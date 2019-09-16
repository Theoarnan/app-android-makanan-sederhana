package com.example.ukrim.aplikasimakanan.networking

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    val BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    fun create() : RetrofitService{
        var builder = OkHttpClient().newBuilder()
        builder.connectTimeout(15,TimeUnit.SECONDS)
        val retrofit = Retrofit.Builder()
            .client(builder.build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(RetrofitService::class.java)
    }
}