package com.fastival.dagger2hardex.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fastival.dagger2hardex.SessionManager
import com.fastival.dagger2hardex.models.Resource
import com.fastival.dagger2hardex.models.User
import java.lang.ref.WeakReference
import javax.inject.Inject

class ProfileViewModel: ViewModel {

    private val refSessionManager: WeakReference<SessionManager>

    @Inject
    constructor(sessionManager: SessionManager) {
        this.refSessionManager = WeakReference(sessionManager)
        Log.d("Main" , "ProfileViewModel: viewModel is ready")
    }

    fun getAuthenticateUser(): LiveData<Resource<User>> {
        return refSessionManager.get()!!.getAuthUser()
    }
}