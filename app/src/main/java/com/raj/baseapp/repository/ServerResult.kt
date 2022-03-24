package com.raj.baseapp.repository

sealed class ServerResult<out S> {
    data class Success<out T>(val data: T) : ServerResult<T>()
    data class Error(val exception: Exception) : ServerResult<Nothing>()
}