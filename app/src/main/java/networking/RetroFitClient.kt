package networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetroFitClient {
    private val okHttpClient = OkHttpClient.Builder().build()
    private const val BASE_URL = "https://api.escuelajs.co/api/v1/"
    val retroFit = Retrofit.Builder().client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}