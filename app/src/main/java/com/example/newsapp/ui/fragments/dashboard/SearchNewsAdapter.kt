package com.example.newsapp.ui.fragments.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inshortsclone.databinding.RvItemsNewsSearchBinding
import com.example.newsapp.data.models.Article

class SearchNewsAdapter(private val articles: ArrayList<Article>) :
    RecyclerView.Adapter<SearchNewsAdapter.SearchViewHolder>() {

    class SearchViewHolder(private val binding: RvItemsNewsSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(article: Article) {
            binding.tvNewsTitle.text = article.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = RvItemsNewsSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(articles[position])
    }
}