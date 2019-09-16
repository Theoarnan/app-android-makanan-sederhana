package com.example.ukrim.aplikasimakanan.ui.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ukrim.aplikasimakanan.R
import com.example.ukrim.aplikasimakanan.ui.DessertFragment
import com.example.ukrim.aplikasimakanan.ui.SeafoodFragment
import kotlinx.android.synthetic.main.fragment_favorite_container.*

class FavoriteContainerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_container, container, false)
    }

    companion object{
        fun newInstance() : FavoriteContainerFragment = FavoriteContainerFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewPager(vp_container)
        tab_layout.setupWithViewPager(vp_container)
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(FavoriteSeafoodFragment.newInstance(), "Seafood")
        adapter.addFragment(FavoriteDessertFragment.newInstance(), "Dessert")
        viewPager?.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager : FragmentManager):FragmentPagerAdapter(manager){
        private val mFragmentList : MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList : MutableList<String> = ArrayList()


        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentTitleList.size
        }
        fun addFragment(fragment: Fragment, title: String){
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList.get(position)
        }
    }
}

