package com.example.newsapp.ui.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.inshortsclone.R
import com.example.inshortsclone.databinding.ActivityMainBinding
import com.example.newsapp.ui.fragments.dashboard.DashboardFragment
import com.example.newsapp.ui.fragments.listings.ListingsFragment
import com.example.newsapp.utils.Constants

class MainActivity : AppCompatActivity() {

    lateinit var vpAdapter: ViewPagerAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        setupViewPager()
    }

    private fun setupViewPager() {
        vpAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        vpAdapter.addFragment(DashboardFragment.newInstance())
        vpAdapter.addFragment(ListingsFragment.newInstance())

        binding.vpRoot.adapter = vpAdapter
        binding.vpRoot.setCurrentItem(Constants.LISTINGS_TAB_POS, false)
    }
}