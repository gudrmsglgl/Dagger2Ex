package com.fastival.dagger2hardex.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.fastival.dagger2hardex.SessionManager
import com.fastival.dagger2hardex.models.Resource
import com.fastival.dagger2hardex.models.User
import com.fastival.dagger2hardex.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel : ViewModel {

    private val sessionManager: SessionManager // @Singleton scoped
    //private val authUser: MediatorLiveData<Resource<User>> = MediatorLiveData()
    private var authApi: AuthApi // @AuthScope scoped dependency

    @Inject
    constructor(authApi: AuthApi, sessionManager: SessionManager) {
        Log.d("Main", "AuthViewModel created() and work")

        this.authApi = authApi
        this.sessionManager = sessionManager
    }

    fun observeUser(): LiveData<Resource<User>> = sessionManager.getAuthUser()
    //fun observeUser(): LiveData<Resource<User>> = authUser

    fun authenticateWithId(id: Int) {
        Log.d("Main", "attemptLogin: attempting to login.")
        sessionManager.authenticateWithId(queryUserId(id))
    }

    private fun queryUserId(userId: Int): LiveData<Resource<User>> {
        return LiveDataReactiveStreams.fromPublisher(
            /*authApi.getUser(id)
                .subscribeOn(Schedulers.io())* --> error don't handling */
            authApi.getUser(userId)
                .onErrorReturn {
                    val errorUser = User().apply {
                        this.id = -1
                    }
                    errorUser
                }
                .map {
                    if (it.id == -1) Resource.Error("Could not authenticate", null)
                    //if (it.id == -1) AuthResource.error("Could not authenticate", null)
                    else Resource.Success(it)
                    //AuthResource.authenticated(it)
                }
                .subscribeOn(Schedulers.io())
        )
    }

}