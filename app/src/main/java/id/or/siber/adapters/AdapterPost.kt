package id.or.siber.adapters

import android.content.Intent
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.or.siber.DetailActivity
import id.or.siber.R
import id.or.siber.models.post.ModelPostItem
import id.or.siber.utils.HtmlUtils
import java.text.SimpleDateFormat
import java.util.Calendar

class AdapterPost(private val posts: MutableList<ModelPostItem>, private val thumbnailUrls: MutableMap<Int, String>,
                  private val categories: MutableMap<Int, String>) :
    RecyclerView.Adapter<AdapterPost.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val dateTime: TextView = itemView.findViewById(R.id.dateTextView)
        val thumnails: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(R.drawable.no_img)
            .into(holder.thumnails)
        val post = posts[position]
        val thumbnailUrl = thumbnailUrls[post.featuredMedia]
        val category = categories[post.categories.first()]
        if (thumbnailUrl != null) {
            Glide.with(holder.itemView.context)
                .load(thumbnailUrl)
                .placeholder(R.drawable.no_img)
                .error(R.drawable.no_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumnails)
        }
        holder.titleTextView.text = post?.title?.rendered?.let { HtmlUtils.fromHtmlLimit(it, 30) }
        holder.contentTextView.text = post?.content?.rendered?.let { HtmlUtils.fromHtmlLimit(it, 90) }

//        var urlMedia = ""
//        if (post.links.wpFeaturedmedia != null && !post.links.wpFeaturedmedia.isEmpty()) {
//            val url = post.links.wpFeaturedmedia.first().href
//            val lastSegment = url.substringAfterLast("/")
//            val lastId = lastSegment.toInt()
//
//            val apiService = RetrofitClient.retrofit.create(ApiService::class.java)
//            val getData = apiService.getPostsMedia(lastId)
//            getData.enqueue(object : Callback<ModelMedia> {
//            override fun onResponse(
//                call: Call<ModelMedia>,
//                response: Response<ModelMedia>
//            ) {
//                if (response.isSuccessful) {
//                    response.body()?.let { data ->
//                        val data = response.body()
//                        urlMedia = data?.guid?.rendered ?: ""
//                        Glide.with(holder.itemView.context)
//                            .load(urlMedia)
//                            .placeholder(R.drawable.no_img)
//                            .error(R.drawable.no_img)
//                            .diskCacheStrategy(DiskCacheStrategy.ALL)
//                            .into(holder.thumnails)
//                    }
//                } else {
//                    // Handle API error response
//                }
//            }
//
//            override fun onFailure(call: Call<ModelMedia>, t: Throwable) {
//                // Handle failure
//            }
//        })
//        }

        val dateString = post?.date
        val calendar = Calendar.getInstance()
        calendar.setTime(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateString))
        val localDate = DateUtils.formatDateTime(holder.itemView.context, calendar.timeInMillis,
            DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_SHOW_DATE or
                    DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_WEEKDAY
        )
        holder.dateTime.text = localDate

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("title", post?.title?.rendered?.let { HtmlUtils.fromHtml(it) })
            intent.putExtra("content", post?.content?.rendered?.let { HtmlUtils.fromHtml(it) })
//            intent.putExtra("media", urlMedia)
            intent.putExtra("media", thumbnailUrl)
            intent.putExtra("date", localDate)
            intent.putExtra("guid", post?.guid?.rendered)
            intent.putExtra("category", category)
            holder.itemView.context.startActivity(intent)
        }
    }

    // Method to add a single item
//    fun addItem(newItem: ModelPostItem) {
//        posts.add(newItem)
//        notifyItemInserted(posts.size - 1)
//    }

    // Method to add all items from a list
    fun addAll(newItems: List<ModelPostItem>, newThumnails: MutableMap<Int, String>,
               newCategories: MutableMap<Int, String>) {
        val startIndex: Int = posts.size
        posts.addAll(newItems)
        notifyItemRangeInserted(startIndex, newItems.size)

        val i: Int = thumbnailUrls.size
        thumbnailUrls.putAll(newThumnails)
        notifyItemRangeInserted(i, newThumnails.size)

        val j: Int = categories.size
        categories.putAll(newCategories)
        notifyItemRangeInserted(j, newCategories.size)
    }

    override fun getItemCount() = posts.size

    fun clear() {
        val size: Int = posts.size
        posts.clear()
        notifyItemRangeRemoved(0, size)

        val sizeThumnail: Int = thumbnailUrls.size
        thumbnailUrls.clear()
        notifyItemRangeRemoved(0, sizeThumnail)

        val sizeCategory: Int = categories.size
        categories.clear()
        notifyItemRangeRemoved(0, sizeCategory)
    }
}
