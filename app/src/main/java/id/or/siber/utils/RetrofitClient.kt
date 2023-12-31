package id.or.siber.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private const val BASE_URL = "https://siber.or.id" // Replace with your API base URL
    var httpClient: OkHttpClient =
        OkHttpClient.Builder() //here we can add Interceptor for dynamical adding headers
//            .addNetworkInterceptor(Interceptor { chain ->
//                val request: Request =
//                    chain.request().newBuilder().addHeader("test", "test").build()
//                chain.proceed(request)
//            }) //here we adding Interceptor for full level logging
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
}
