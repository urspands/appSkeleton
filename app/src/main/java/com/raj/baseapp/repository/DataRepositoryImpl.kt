package com.raj.baseapp.repository

import com.raj.baseapp.api.FeedResponse
import com.raj.baseapp.api.NetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DataRepositoryImpl(private val networkApi: NetworkApi) : DataRepository {
    override suspend fun getFeedData(nextPageId: String): ServerResult<FeedResponse> {
        return withContext(Dispatchers.Main) {
            try {
                val response = networkApi.getNewsFeed(nextPageId)
                ServerResult.Success(response)
            } catch (exception: Exception) {
                ServerResult.Error(exception)
            }
        }
    }

}