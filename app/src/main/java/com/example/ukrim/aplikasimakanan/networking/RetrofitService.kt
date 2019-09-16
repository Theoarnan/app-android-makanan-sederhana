package com.example.ukrim.aplikasimakanan.networking

import com.example.ukrim.aplikasimakanan.model.MealDetailResponse
import com.example.ukrim.aplikasimakanan.model.MealResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("filter.php")
    fun getAllMealsByCategory(@Query("c")type: String)
    : Deferred<MealResponse>

    @GET("lookup.php")
    fun getAllMealsById(@Query("i")type: String)
            : Deferred<MealDetailResponse>
}