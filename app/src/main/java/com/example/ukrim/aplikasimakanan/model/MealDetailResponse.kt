package com.example.ukrim.aplikasimakanan.model
import com.google.gson.annotations.SerializedName


data class MealDetailResponse(
    @SerializedName("meals")
    val mealsDetail: List<MealDetail>
)

data class MealDetail(
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strInstructions")
    val strInstructions: String,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String
)