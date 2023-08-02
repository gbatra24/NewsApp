package com.example.newsapp.ui.fragments.dashboard

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inshortsclone.R
import com.example.inshortsclone.databinding.FragmentDashboardBinding
import com.example.newsapp.data.models.Article
import com.example.newsapp.data.models.ListingsResponse
import com.google.android.material.divider.MaterialDividerItemDecoration

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: DashboardViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setSearchInput()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        @JvmStatic
        fun newInstance() =
            DashboardFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun setupObservers() {
        viewModel.searchResponseLiveData.observe(viewLifecycleOwner) {
            setupSearchedNewsRv(it.articles)
        }
    }

    private fun setSearchInput() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().trim().isEmpty()) {
                    adapter = SearchNewsAdapter(ArrayList())
                    binding.rvSearchNews.adapter = adapter
                }
            }

        })

        binding.etSearch.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.searchNews(binding.etSearch.text.toString())
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            true
        }
    }

    private lateinit var adapter: SearchNewsAdapter

    private fun setupSearchedNewsRv(articles: ArrayList<Article>) {
        adapter = SearchNewsAdapter(articles)
        binding.rvSearchNews.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvSearchNews.adapter = adapter

        val decoration = MaterialDividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).apply {
            dividerInsetEnd = 28
            dividerInsetStart = 28
            dividerColor = ContextCompat.getColor(requireContext(),R.color.black)
        }
        binding.rvSearchNews.addItemDecoration(decoration)
    }
}