package com.fastival.dagger2hardex.ui.auth

class AuthResource<T>{

    val authStatus: AuthStatus
    val data: T?
    val message: String?

    constructor(authStatus: AuthStatus, data: T?, message: String?) {
        this.authStatus = authStatus
        this.data = data
        this.message = message
    }

    companion object{
        fun <T> authenticated (data: T?) = AuthResource(AuthStatus.AUTHENTICATED, data, null)
        fun <T> error (msg: String?, data: T?) = AuthResource(AuthStatus.ERROR, data, msg)
        fun <T> loading (data: T?) = AuthResource(AuthStatus.LOADING, data, null)
        fun <T> logout () = AuthResource(AuthStatus.NOT_AUTHENTICATED, null, null)
    }

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }
}