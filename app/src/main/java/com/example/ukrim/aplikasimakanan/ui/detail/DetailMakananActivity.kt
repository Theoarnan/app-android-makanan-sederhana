package com.example.ukrim.aplikasimakanan.ui.detail

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import com.bumptech.glide.Glide
import com.example.ukrim.aplikasimakanan.R
import com.example.ukrim.aplikasimakanan.database.database
import com.example.ukrim.aplikasimakanan.database.entity.MealEntity
import com.example.ukrim.aplikasimakanan.model.MealDetail
import com.example.ukrim.aplikasimakanan.presenter.MealPresenter
import com.example.ukrim.aplikasimakanan.util.Constant
import com.example.ukrim.aplikasimakanan.view.CommonView
import kotlinx.android.synthetic.main.activity_detail_makanan.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import java.sql.SQLClientInfoException

class DetailMakananActivity : AppCompatActivity(), CommonView {
    private  var isFavorite : Boolean = false
    private lateinit var mealId : String
    private lateinit var presenter : MealPresenter
    private lateinit var mealDetail : MealDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_makanan)

        mealId = intent.getStringExtra(Constant.KEY_IDMEAL)
        presenter = MealPresenter(this, this)
        presenter.getAllMealById(mealId)
        fav_button.setOnClickListener{
            if(isFavorite) addToFavorite()else removeFromFavorite()

        }
        setFavorite()

    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun error(error: Throwable) {

    }

    override fun success(anyResponse: Any) {
        var mealDetail = anyResponse as MealDetail
        tv_nama_makanan.text = mealDetail.strMeal
        tv_kategori_makanan.text = mealDetail.strCategory
        tv_instruksi.text = mealDetail.strInstructions
        Glide.with(this)
            .load(mealDetail.strMealThumb)
            .into(image_makanan)
        favoriteState()
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    MealEntity.TABLE_FAVORITE,
                    MealEntity.ID_MEAL to mealDetail.idMeal,
                MealEntity.NAME_MEAL to mealDetail.strMeal,
                MealEntity.IMAGE_MEAL to mealDetail.strMealThumb,
                MealEntity.CATEGORY_MEAL to mealDetail.strCategory,
                MealEntity.INSTRUCTION_MEAL to mealDetail.strInstructions
                )
            }
            toast("Ditambhkan ke Favorite")
            isFavorite = true
        }catch (e:SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(MealEntity.TABLE_FAVORITE,
                    "(${MealEntity.ID_MEAL} = {id})",
                    MealEntity.ID to mealDetail.idMeal
                )
            }
            toast("Dihapus dari favorie")
            isFavorite = false
        }catch (e:SQLiteConstraintException){

        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(MealEntity.TABLE_FAVORITE)
                .whereArgs("(${MealEntity.ID_MEAL} = {id})",
                "id" to mealDetail.idMeal
            )
            val favorite = result.parseList(classParser<MealEntity>())
            if(favorite.isEmpty()) isFavorite = true
        }

    }

    private fun setFavorite(){
        if(isFavorite)
            fav_button.setImageResource(R.drawable.ic_star_white)
        else
            fav_button.setImageResource(R.drawable.ic_star_border)
    }
}
