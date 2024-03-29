package com.example.newsapp.ui.fragments.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.NetworkService
import com.example.newsapp.data.api.RetrofitClient
import com.example.newsapp.data.models.ListingsResponse
import com.example.newsapp.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {

    var searchResponseLiveData = MutableLiveData<ListingsResponse>()
    var searchErrorLiveData = MutableLiveData<String>()

    fun searchNews(query: String) {
        var client = RetrofitClient.getInstance()
        var service = client.create(NetworkService::class.java)

        viewModelScope.launch {
            service.getNewsBySearch(Constants.API_KEY, query, "popularity")
                .enqueue(object : Callback<ListingsResponse> {
                    override fun onResponse(
                        call: Call<ListingsResponse>,
                        response: Response<ListingsResponse>
                    ) {
                        if (response.isSuccessful) {
                            searchResponseLiveData.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<ListingsResponse>, t: Throwable) {
                        searchErrorLiveData.value = t.message
                    }
                })
        }
    }
}