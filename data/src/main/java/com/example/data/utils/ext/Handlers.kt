package com.example.data.utils.ext

import com.squareup.moshi.Moshi
import retrofit2.HttpException

internal inline fun <reified T, R> handleHttpError(
    ex: HttpException,
    errorMapper: (T) -> R
): R {
    val errorBody = ex.response()?.errorBody()?.string()
    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter(T::class.java)
    val errorDto = errorBody?.let { adapter.fromJson(it) }
    val errorResponse = errorDto?.let { errorMapper(it) }
    return errorResponse ?: error("Unknown error")
}