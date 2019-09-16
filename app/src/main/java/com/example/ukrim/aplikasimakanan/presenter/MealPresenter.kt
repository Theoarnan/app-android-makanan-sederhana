package com.example.ukrim.aplikasimakanan.presenter

import android.content.Context
import com.example.ukrim.aplikasimakanan.networking.RetrofitFactory
import com.example.ukrim.aplikasimakanan.view.CommonView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MealPresenter(
    private val view : CommonView,
    private val context : Context
)
{
    fun getAllMealsCategory(category: String){
        view.showLoading()
        val api = RetrofitFactory.create()
        val request = api.getAllMealsByCategory(category)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.await()
                view.success(response)
            }catch (e : Exception){
                view.error(e)
            }
            view.hideLoading()
        }
    }
    fun getAllMealById(mealId: String){
        view.showLoading()
        val api = RetrofitFactory.create()
        val request = api.getAllMealsById(mealId)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.await()
                view.success(response.mealsDetail[0])
            }catch (e : Exception){
                view.error(e)
            }
            view.hideLoading()
        }
    }

}