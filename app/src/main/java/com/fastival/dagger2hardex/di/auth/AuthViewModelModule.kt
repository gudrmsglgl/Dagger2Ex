package com.fastival.dagger2hardex.di.auth

import androidx.lifecycle.ViewModel
import com.fastival.dagger2hardex.di.ViewModelKey
import com.fastival.dagger2hardex.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

}