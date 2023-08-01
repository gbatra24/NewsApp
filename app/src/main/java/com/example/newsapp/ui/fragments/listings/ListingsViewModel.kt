package com.example.newsapp.ui.fragments.listings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.api.NetworkService
import com.example.newsapp.data.api.RetrofitClient
import com.example.newsapp.data.models.ListingsResponse
import com.example.newsapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListingsViewModel : ViewModel() {

    var listingsResponseLiveData = MutableLiveData<ListingsResponse>()
    var listingsErrorLiveData = MutableLiveData<String>()

    fun getTopHeadlines() {
        val client = RetrofitClient.getInstance()
        val service = client.create(NetworkService::class.java)

        service.getTopHeadlines(Constants.API_KEY, "in", 1, 10)
            .enqueue(object : Callback<ListingsResponse> {
                override fun onResponse(
                    call: Call<ListingsResponse>,
                    response: Response<ListingsResponse>
                ) {
                    if (response.isSuccessful) {
                        listingsResponseLiveData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ListingsResponse>, t: Throwable) {
                    listingsErrorLiveData.value = t.message
                }

            })

    }

}