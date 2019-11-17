package com.fastival.dagger2hardex.models

sealed class Resource<out T>
    (val data: T? = null, val message: String? = null)
{
    class Init<out T>() : Resource<T>()
    class Success<out T>(data: T) : Resource<T>(data)
    class Loading<out T>(data: T? = null) : Resource<T>(data)
    class Error<out T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Logout<out T>(data: T? = null) : Resource<T>(data)
}