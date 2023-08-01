package com.example.newsapp.ui.fragments.listings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.inshortsclone.databinding.FragmentListingsBinding
import com.example.newsapp.data.models.Article


class ListingsFragment : Fragment() {

    companion object {
        fun newInstance() = ListingsFragment()
    }

    private lateinit var viewModel: ListingsViewModel
    private lateinit var binding: FragmentListingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListingsViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopHeadlines()

        viewModel.listingsResponseLiveData.observe(viewLifecycleOwner) {
            setupNewsRv(it.articles)
        }

        viewModel.listingsErrorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun setupNewsRv(newsList: ArrayList<Article>) {
        val adapter = ListingsAdapter(newsList)
        binding.rvNews.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvNews.adapter = adapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvNews)

    }

}