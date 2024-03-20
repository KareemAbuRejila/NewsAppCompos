package edu.training.myapplication.data.remote

import edu.training.myapplication.data.remote.dto.NewsResponse
import edu.training.myapplication.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse


    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY

    ): NewsResponse
}