package com.demo.orders.retrofit

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClientInstance {

    var BaseURL = "http://developer.scoto.in/"
    var gson = GsonBuilder()
            .setLenient()
            .create()

    private val builder = Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
    private var retrofit: Retrofit? = null

    private val httpClient = OkHttpClient.Builder()
    private val TAG = RetrofitClientInstance::class.java.simpleName


    fun createServices(serviceClass: Class<ApiService>, BaseURL: String): ApiService {
        Log.e(TAG, "createServices: ")
        val builder = Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()
        }

        val authToken = Credentials.basic("admin", "1234")
        Log.e(TAG, "createServices:authToken : $authToken")
        val interceptor = AuthenticationInterceptor(authToken)
        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor)
            builder.client(httpClient.build())
            retrofit = builder.build()
        }
        return retrofit!!.create(serviceClass)
    }
}
