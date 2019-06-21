package com.afrasilv.meeptest.data.repository

sealed class Result <out T : Any> {
    class Success<out T : Any>(val data: T) : Result<T>()
    object Error : Result<Nothing>()
}