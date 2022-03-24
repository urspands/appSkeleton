package com.raj.baseapp.api

import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApi {
    companion object {
        const val BASE_URL = "https://andrewmunn.github.io"
    }

    @GET("/newsfeed/page{nextPageId}.json")
    suspend fun getNewsFeed(@Path("nextPageId") nextPageId: String): FeedResponse
}