package com.example.ukrim.aplikasimakanan.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.ukrim.aplikasimakanan.R
import com.example.ukrim.aplikasimakanan.model.Meal

class MakananAdapter(
    private val meals : MutableList<Meal>,
    private val listener : Listener
) : RecyclerView.Adapter<MakananAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakananAdapter.ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_makanan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: MakananAdapter.ViewHolder, position: Int) {
        holder.bindModel(meals.get(position), listener)
    }

    interface Listener {
        fun onItemClick(meal: Meal)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaMakanan : TextView = itemView.findViewById(R.id.nama_makanan)
        val ivImageMakanan : ImageView = itemView.findViewById(R.id.img_makanan)
        fun bindModel(meal : Meal, listener: Listener){
            tvNamaMakanan.text = meal.strMeal
            Glide.with(itemView.context).load(meal.strMealThumb).into(ivImageMakanan)
            itemView.setOnClickListener{
                listener.onItemClick(meal)
            }
    }
}
}