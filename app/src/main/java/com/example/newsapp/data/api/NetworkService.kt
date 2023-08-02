package com.example.newsapp.data.api

import com.example.newsapp.data.models.ListingsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("v2/top-headlines")
    fun getTopHeadlines(@Query("apiKey") apiKey: String,
                        @Query("country") country: String,
                        @Query("page") page: Int = 1,
                        @Query("pageSize") pageSize: Int) : Call<ListingsResponse>

    @GET("v2/everything")
    fun getNewsBySearch(@Query("apiKey") apiKey: String,
                        @Query("q") query: String,
                        @Query("sortBy") sortBy:String) : Call<ListingsResponse>

}