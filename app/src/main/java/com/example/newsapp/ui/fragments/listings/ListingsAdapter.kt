package com.example.newsapp.ui.fragments.listings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inshortsclone.databinding.RvItemNewsListingsBinding
import com.example.newsapp.data.models.Article


class ListingsAdapter(private val articles: ArrayList<Article>):
    RecyclerView.Adapter<ListingsAdapter.ListingsViewHolder>() {

    class ListingsViewHolder(private val binding: RvItemNewsListingsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvAuthor.text = article.source.name
            binding.tvDesc.text = article.content

            Glide
                .with(binding.ivThumbnail)
                .load(article.urlToImage)
                .centerCrop()
                .into(binding.ivThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingsViewHolder {
        val binding = RvItemNewsListingsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListingsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ListingsViewHolder, position: Int) {
        holder.onBind(articles[position])
    }
}