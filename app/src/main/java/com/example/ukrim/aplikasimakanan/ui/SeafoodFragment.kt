package com.example.ukrim.aplikasimakanan.ui



import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.view.menu.MenuWrapperFactory
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ukrim.aplikasimakanan.R
import com.example.ukrim.aplikasimakanan.adapter.MakananAdapter
import com.example.ukrim.aplikasimakanan.model.Meal
import com.example.ukrim.aplikasimakanan.model.MealResponse
import com.example.ukrim.aplikasimakanan.presenter.MealPresenter
import com.example.ukrim.aplikasimakanan.ui.detail.DetailMakananActivity
import com.example.ukrim.aplikasimakanan.util.Constant
import com.example.ukrim.aplikasimakanan.view.CommonView
import kotlinx.android.synthetic.main.fragment_meals.*

class SeafoodFragment : Fragment(), CommonView, MakananAdapter.Listener {
    lateinit var presenter : MealPresenter
    lateinit var adapter : MakananAdapter
    var meals : MutableList<Meal> = mutableListOf()

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun error(error: Throwable) {

    }

    override fun success(anyResponse: Any) {
        val dataResponse : MealResponse = anyResponse as MealResponse
        meals.clear()
        meals.addAll(dataResponse.meals)
        adapter.notifyDataSetChanged()
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun onItemClick(meal: Meal) {
        val intent =  Intent(activity?.applicationContext, DetailMakananActivity::class.java)
        intent.putExtra(Constant.KEY_IDMEAL,meal.idMeal)
        startActivity(intent)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setDataMakanan()

    }
    private fun dpToPx(dp : Int): Int{
        val r = resources
        return Math.round(TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics
        ))
    }
    private fun setDataMakanan(){
        list_makanan.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(activity!!.applicationContext, 2)
        list_makanan.layoutManager = layoutManager
        list_makanan.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(8), true))
        list_makanan.itemAnimator = DefaultItemAnimator()
        adapter = MakananAdapter(meals, this)
        list_makanan.adapter = adapter
        presenter = MealPresenter(this, activity!!.applicationContext)
        presenter.getAllMealsCategory("Seafood")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals, container, false)
    }

    companion object {
        fun newInstance(): SeafoodFragment = SeafoodFragment()
    }

    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            if (includeEdge) {
                outRect.left =
                    spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right =
                    (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right =
                    spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }


}
