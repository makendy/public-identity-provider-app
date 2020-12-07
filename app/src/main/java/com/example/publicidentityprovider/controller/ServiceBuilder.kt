package com.example.publicidentityprovider.controller
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()
    private const val API_BASE_URL = "http://192.168.0.23:5000"
    private val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())

    private val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(jsonConverter)
            .client(client)
            .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}