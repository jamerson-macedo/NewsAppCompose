package com.example.newsappcompose.data.remote

import com.example.newsappcompose.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") country: String,
        @Query("apiKey") apiKey: String="40bf988aafb04ef49f9402e0a66f669a"
    ): NewsResponse
}