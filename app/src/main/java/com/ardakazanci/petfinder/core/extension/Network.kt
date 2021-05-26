package com.ardakazanci.petfinder.core.extension

import com.ardakazanci.petfinder.R
import com.ardakazanci.petfinder.core.base.BaseBean
import com.ardakazanci.petfinder.core.exception.ErrorHandler
import com.ardakazanci.petfinder.core.functional.Either
import com.google.gson.JsonSyntaxException
import org.json.JSONException
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse
import java.net.SocketTimeoutException

suspend fun <T, R> request(
    call: Call<BaseBean<T>>,
    transform: (T) -> R,
    default: T
): Either<ErrorHandler, R> {
    return try {
        val response = call.awaitResponse()
        when (response.isSuccessful && response.body() != null && response.body()?.errorCode == 0) {
            true -> Either.Right(transform(response.body()?.data ?: default))
            false -> Either.Left(handleFailure(response))
        }
    } catch (exception: Throwable) {
        when (exception) {
            is JSONException -> ErrorHandler.JsonSyntaxError
            is JsonSyntaxException -> ErrorHandler.JsonSyntaxError
            is IllegalArgumentException -> ErrorHandler.JsonSyntaxError
            is SocketTimeoutException -> ErrorHandler.SocketTimeoutError
            else -> ErrorHandler.ServerError
        }.run {
            Either.Left(this)
        }
    }
}


fun <T> handleFailure(response: Response<BaseBean<T>>): ErrorHandler {
    return when {
        !response.isSuccessful -> {
            ErrorHandler.ServerError
        }
        response.body() == null -> {
            ErrorHandler.ServerError
        }
        response.body()?.errorCode != 0 -> {
            ErrorHandler.BusinessLogicError(response.body()?.errorCode, response.body()?.errorMsg)
        }
        else -> {
            ErrorHandler.ServerError
        }
    }
}

fun failureVerdict(failure: ErrorHandler?): Int {
    return when (failure) {
        is ErrorHandler.NetworkConnection -> (R.string.failure_network_connection);
        is ErrorHandler.ServerError -> (R.string.failure_server_error);
        is ErrorHandler.JsonSyntaxError -> (R.string.failure_data_error);
        else -> (R.string.failure_server_error);
    }
}
