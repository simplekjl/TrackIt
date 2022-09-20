package com.simplekjl.domain.utils

inline fun <T> tryToExecute(block: () -> T): Result<T> {
    return try {
        Result.Success(block())
    } catch (e: Exception) {
        Result.Error(e)
    }
}