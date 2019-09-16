package com.example.ukrim.aplikasimakanan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDialogFragment
import android.util.Log
import android.widget.FrameLayout
import com.example.ukrim.aplikasimakanan.model.MealDetail
import com.example.ukrim.aplikasimakanan.model.MealResponse
import com.example.ukrim.aplikasimakanan.presenter.MealPresenter
import com.example.ukrim.aplikasimakanan.ui.DessertFragment
import com.example.ukrim.aplikasimakanan.ui.SeafoodFragment
import com.example.ukrim.aplikasimakanan.ui.favorite.FavoriteContainerFragment
import com.example.ukrim.aplikasimakanan.view.CommonView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var fragmentContainer : FrameLayout? = null
    private lateinit var textMessege: Message

    fun addFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container,fragment,fragment.javaClass.simpleName)
            .commit()
    }

    private val onBottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.menu_seafood -> {
                val fragment = SeafoodFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_dessert -> {
                val fragment = DessertFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_favorite -> {
                val fragment = FavoriteContainerFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mHandler = Handler()
        val mPendingRunnable = Runnable {
            bottom_navigation.setOnNavigationItemSelectedListener(onBottomNavItemSelectedListener)

            val defaultFragment = SeafoodFragment.newInstance()
            addFragment(defaultFragment)
        }
        if(mPendingRunnable != null){
            mHandler.post(mPendingRunnable)
        }
    }

}
