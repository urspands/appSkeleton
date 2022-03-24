package com.raj.baseapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raj.baseapp.api.Feed
import com.raj.baseapp.repository.DataRepository
import com.raj.baseapp.repository.ServerResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {

    private val _feedResponse: MutableLiveData<List<Feed>> = MutableLiveData()
    val feedResponse: MutableLiveData<List<Feed>> get() = _feedResponse
    private var _nextPageId: String? = "1"
    val _feeds = ArrayList<Feed>()
    fun getFeedDataFromServer() {
        _nextPageId?.let {
            viewModelScope.launch {
                Log.d(TAG, "getFeedDataFromServer: nextPageID ->$_nextPageId")
                when (val feedResponse = dataRepository.getFeedData(it)) {
                    is ServerResult.Error -> {
                        Log.d(
                            TAG,
                            "getFeedDataFromServer: Error:${feedResponse.exception.localizedMessage}"
                        )
                    }
                    is ServerResult.Success -> {
                        Log.d(
                            TAG,
                            "getFeedDataFromServer:Response count-> ${feedResponse.data.stories.size} "
                        )

                        _feeds.addAll(feedResponse.data?.stories)
                        _feedResponse.value = feedResponse.data?.stories
                        _nextPageId = feedResponse.data?.nextPageId

                    }
                }
            }
        }


    }

    companion object {
        const val TAG = "MainActivityViewModel"
    }
}