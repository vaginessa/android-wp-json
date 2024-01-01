package id.or.siber

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.appbar.AppBarLayout
import id.or.siber.adapters.AdapterDrawer
import id.or.siber.adapters.AdapterPost
import id.or.siber.interfaces.ApiService
import id.or.siber.models.category.ModelCategoryItem
import id.or.siber.models.detailpost.ModelCategoryId
import id.or.siber.models.media.ModelMedia
import id.or.siber.models.post.ModelPostItem
import id.or.siber.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterDrawer: AdapterDrawer
    private val apiService = RetrofitClient.retrofit.create(ApiService::class.java)
    private var posts: MutableList<ModelPostItem> = mutableListOf()
    val thumbnailUrls = mutableMapOf<Int, String>()
    val categories = mutableMapOf<Int, String>()

    private lateinit var rvPost: RecyclerView
    private lateinit var adapterPost: AdapterPost

    var searchQuery = ""
    var category = 0
    var currentPage = 1
    val perPage = 5
    var isLoading = false
    val visibleThreshold = 5
    private lateinit var progressBar: ProgressBar

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var getPosts: Call<List<ModelPostItem>>

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ab_gradient_horizontal))
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        recyclerView = findViewById(R.id.drawer_recycler_view)
        progressBar = findViewById(R.id.progressBar)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, android.R.string.ok, android.R.string.no)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        adapterDrawer = AdapterDrawer(emptyList()) { selectedItem ->
            // Handle item click
            handleNavigation(selectedItem)
            drawerLayout.closeDrawers()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterDrawer

        val call = apiService.getDrawerItems()
        call.enqueue(object : Callback<List<ModelCategoryItem>> {
            override fun onResponse(
                call: Call<List<ModelCategoryItem>>,
                response: Response<List<ModelCategoryItem>>
            ) {
                if (response.isSuccessful) {
                    val drawerItems = response.body()
//                    Log.d("cekResponse", drawerItems.toString())
                    drawerItems?.let { adapterDrawer.updateItems(it) }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<List<ModelCategoryItem>>, t: Throwable) {
                // Handle failure
            }
        })

        rvPost = findViewById(R.id.rvPost)
        val layoutManager = LinearLayoutManager(this)
        rvPost.layoutManager = layoutManager
        adapterPost = AdapterPost(posts, thumbnailUrls, categories)
        rvPost.adapter = adapterPost

//        val layoutManager = LinearLayoutManager(this)
//        rvPost.layoutManager = layoutManager
        rvPost.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    loadMorePosts()
                    isLoading = true
                    progressBar.visibility = View.VISIBLE
                }
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchQuery = ""
            category = 0
            currentPage = 1
            initData()
        }

        initData()
    }

    fun initData(){
        swipeRefreshLayout.setRefreshing(true)
        if (category != 0) {
            getPosts = apiService.getPostsByCategory(5, 1, category)
        } else {
            getPosts = apiService.getPosts(5, 1, searchQuery)
        }
        getPosts.enqueue(object : Callback<List<ModelPostItem>> {
            override fun onResponse(
                call: Call<List<ModelPostItem>>,
                response: Response<List<ModelPostItem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->

                        adapterPost.clear()
                        val newPosts = response.body() ?: emptyList()
                        newPosts.forEach { post ->
                            if (post.featuredMedia > 0) {
                                val service = RetrofitClient.retrofit.create(ApiService::class.java)
                                service.getPostsMedia(post.featuredMedia).enqueue(object : Callback<ModelMedia> {
                                    override fun onResponse(call: Call<ModelMedia>, response: Response<ModelMedia>) {
                                        val media = response.body()
                                        val thumbnailUrl = media?.guid?.rendered ?: ""
                                        if (thumbnailUrl != null) {
                                            thumbnailUrls[post.featuredMedia] = thumbnailUrl
                                        }

                                        // If all thumbnails are fetched, update the RecyclerView
//                                        if (thumbnailUrls.size == posts.size) {
//                                            adapterPost.clear()
//                                            recyclerView.adapter = PostAdapter(posts, thumbnailUrls)
//                                        }
                                    }

                                    override fun onFailure(call: Call<ModelMedia>, t: Throwable) {
                                        // Handle failure
                                    }
                                })
                            }

                            if (post.categories.size > 0) {
                                val service = RetrofitClient.retrofit.create(ApiService::class.java)
                                service.getCategoryById(post.categories.first()).enqueue(object : Callback<ModelCategoryId> {
                                    override fun onResponse(call: Call<ModelCategoryId>, response: Response<ModelCategoryId>) {
                                        val item = response.body()
                                        val category = item?.name ?: ""
                                        if (category != null) {
                                            categories[post.categories.first()] = category
                                        }
                                    }

                                    override fun onFailure(call: Call<ModelCategoryId>, t: Throwable) {
                                        // Handle failure
                                    }
                                })
                            }
                        }
                        updateRecyclerView(newPosts, thumbnailUrls, categories)
                        currentPage++
                        isLoading = false
                        progressBar.visibility = View.GONE
                    }
                    swipeRefreshLayout.setRefreshing(false)
                } else {
                    // Handle API error response
                }
            }

            override fun onFailure(call: Call<List<ModelPostItem>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun loadMorePosts() {
        if (category != 0) {
            getPosts = apiService.getPostsByCategory(perPage, currentPage, category)
        } else {
            getPosts = apiService.getPosts(perPage, currentPage, searchQuery)
        }
        getPosts.enqueue(object : Callback<List<ModelPostItem>> {
            override fun onResponse(
                call: Call<List<ModelPostItem>>,
                response: Response<List<ModelPostItem>>
            ) {
                if (response.isSuccessful) {

                    response.body()?.let { posts ->
                        val newPosts = response.body() ?: emptyList()
                        newPosts.forEach { post ->
                            if (post.featuredMedia > 0) {
                                val service = RetrofitClient.retrofit.create(ApiService::class.java)
                                service.getPostsMedia(post.featuredMedia).enqueue(object : Callback<ModelMedia> {
                                    override fun onResponse(call: Call<ModelMedia>, response: Response<ModelMedia>) {
                                        val media = response.body()
                                        val thumbnailUrl = media?.guid?.rendered ?: ""
                                        if (thumbnailUrl != null) {
                                            thumbnailUrls[post.featuredMedia] = thumbnailUrl
                                        }

                                        // If all thumbnails are fetched, update the RecyclerView
//                                        if (thumbnailUrls.size == posts.size) {
//                                            adapterPost.clear()
//                                            recyclerView.adapter = PostAdapter(posts, thumbnailUrls)
//                                        }
                                    }

                                    override fun onFailure(call: Call<ModelMedia>, t: Throwable) {
                                        // Handle failure
                                    }
                                })
                            }

                            if (post.categories.size > 0) {
                                val service = RetrofitClient.retrofit.create(ApiService::class.java)
                                service.getCategoryById(post.categories.first()).enqueue(object : Callback<ModelCategoryId> {
                                    override fun onResponse(call: Call<ModelCategoryId>, response: Response<ModelCategoryId>) {
                                        val item = response.body()
                                        val category = item?.name ?: ""
                                        if (category != null) {
                                            categories[post.categories.first()] = category
                                        }
                                    }

                                    override fun onFailure(call: Call<ModelCategoryId>, t: Throwable) {
                                        // Handle failure
                                    }
                                })
                            }
                        }
                        updateRecyclerView(newPosts, thumbnailUrls, categories)
                        currentPage++
                        isLoading = false
                        progressBar.visibility = View.GONE
                    }
                } else {
                    // Handle API error response
                }
            }

            override fun onFailure(call: Call<List<ModelPostItem>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun updateRecyclerView(posts: List<ModelPostItem>, thumbnailUrls: MutableMap<Int, String>,
                           categories: MutableMap<Int, String>) {
        if (posts != null) {
            adapterPost.addAll(posts, thumbnailUrls, categories)
            adapterPost.notifyDataSetChanged()
        } else {
            // Handle unsuccessful response
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    private fun handleNavigation(selectedItem: ModelCategoryItem) {
        category = selectedItem.id
        initData()
    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Perform the final search
                query?.let { performSearch(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let { performSearch(it) }
                if (newText.isNullOrEmpty()) {
                    performSearch("")
                }
                return true
            }
        })
        return true
    }

    private fun performSearch(query: String) {
        // Filter the dataset based on the query
//        val filteredPosts = posts.filter { it.title.rendered.contains(query, ignoreCase = true) }
//        updateRecyclerView(filteredPosts, thumbnailUrls)
        adapterPost.clear()
        searchQuery = query
        initData()
    }
}