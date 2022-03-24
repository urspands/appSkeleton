package com.raj.baseapp.repository

import com.raj.baseapp.api.FeedResponse

interface DataRepository {
    suspend fun getFeedData(nextPageId: String): ServerResult<FeedResponse>
}