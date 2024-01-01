package id.or.siber.interfaces

import id.or.siber.models.category.ModelCategoryItem
import id.or.siber.models.detailpost.ModelCategoryId
import id.or.siber.models.media.ModelMedia
import id.or.siber.models.post.ModelPostItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/wp-json/wp/v2/categories") // Replace with your actual endpoint
    fun getDrawerItems(): Call<List<ModelCategoryItem>>

    @GET("/wp-json/wp/v2/categories/{categoryId}")
    fun getCategoryById(@Path("categoryId") categoryId: Int): Call<ModelCategoryId>

    @GET("/wp-json/wp/v2/posts")
    fun getPosts(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("search") query: String
    ): Call<List<ModelPostItem>>

    @GET("/wp-json/wp/v2/posts")
    fun getPostsByCategory(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("categories") categories: Int,
    ): Call<List<ModelPostItem>>

    @GET("/wp-json/wp/v2/media/{mediaId}")
    fun getPostsMedia(@Path("mediaId") mediaId: Int): Call<ModelMedia>
}
