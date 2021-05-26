package com.ardakazanci.petfinder.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardakazanci.petfinder.core.exception.ErrorHandler

abstract class BaseViewModel : ViewModel() {

    private val _failure: MutableLiveData<ErrorHandler> = MutableLiveData()
    val failure: LiveData<ErrorHandler> = _failure

    protected fun handleFailure(failure: ErrorHandler) {
        _failure.value = failure
    }
}