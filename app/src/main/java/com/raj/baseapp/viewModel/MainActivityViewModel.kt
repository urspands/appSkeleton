package com.raj.baseapp.viewModel

import androidx.lifecycle.ViewModel
import com.raj.baseapp.repository.DataRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {
}