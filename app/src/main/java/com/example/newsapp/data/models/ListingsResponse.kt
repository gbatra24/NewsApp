package com.example.newsapp.data.models

data class ListingsResponse(val status: String,
                            val totalResults: Int,
                            val articles: ArrayList<Article>
)
