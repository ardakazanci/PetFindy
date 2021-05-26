package com.ardakazanci.petfinder.core.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ardakazanci.petfinder.core.exception.ErrorHandler

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<ErrorHandler>> LifecycleOwner.failure(liveData: L, body: (ErrorHandler?) -> Unit) =
    liveData.observe(this, Observer(body))