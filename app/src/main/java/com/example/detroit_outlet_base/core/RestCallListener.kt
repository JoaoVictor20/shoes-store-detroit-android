package com.example.detroit_outlet.core

interface RestCallListener<T> {
    fun onSuccess(data: T?, httpStatusCode: Int)
    fun onFailure(throwable: Throwable)
}