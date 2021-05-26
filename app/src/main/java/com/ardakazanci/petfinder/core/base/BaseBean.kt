package com.ardakazanci.petfinder.core.base
import java.io.Serializable

class BaseBean<T> : Serializable {
    var errorCode = -99999
    var errorMsg: String? = null
    internal var data: T? = null
}