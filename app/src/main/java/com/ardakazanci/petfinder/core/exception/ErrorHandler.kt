package com.ardakazanci.petfinder.core.exception

sealed class ErrorHandler {
    object NetworkConnection : ErrorHandler()
    object ServerError : ErrorHandler()
    object JsonSyntaxError : ErrorHandler()
    object SocketTimeoutError : ErrorHandler()
    class BusinessLogicError(val code: Int?, val msg: String?) : ErrorHandler()
    abstract class FeatureError : ErrorHandler()
}