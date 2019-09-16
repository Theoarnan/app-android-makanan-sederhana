package com.example.ukrim.aplikasimakanan.view

import com.example.ukrim.aplikasimakanan.model.MealResponse

interface CommonView {
    fun showLoading()
    fun error(error : Throwable)
    fun success(anyResponse: Any)
    fun hideLoading()
}