package com.example.ukrim.aplikasimakanan.ui.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ukrim.aplikasimakanan.R


class FavoriteDessertFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meals, container, false)
    }
    companion object{
        fun newInstance() : FavoriteDessertFragment = FavoriteDessertFragment()
    }
}



