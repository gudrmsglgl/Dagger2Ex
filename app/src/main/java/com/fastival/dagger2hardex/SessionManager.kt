package com.fastival.dagger2hardex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.fastival.dagger2hardex.models.Resource
import com.fastival.dagger2hardex.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(){

    private val cachedUser: MediatorLiveData<Resource<User>> = MediatorLiveData()

    fun authenticateWithId(source: LiveData<Resource<User>>) {
        if (cachedUser != null) {
            cachedUser.value = Resource.Loading(null)
            cachedUser.addSource(source, Observer {
                cachedUser.value = it
                cachedUser.removeSource(source)
            })
        }
    }

    fun logOut() {
        Log.d("Main","logOut: logging out...")
        cachedUser.value = Resource.Logout(null)
    }

    fun getAuthUser() = cachedUser
}