package com.ardakazanci.petfinder.core.usecase

import com.ardakazanci.petfinder.core.exception.ErrorHandler
import com.ardakazanci.petfinder.core.functional.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<ErrorHandler, Type>

    operator fun invoke(params: Params, onResult: (Either<ErrorHandler, Type>) -> Unit = {}) {
        val job = GlobalScope.async(Dispatchers.IO) { run(params) }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    class None
}
