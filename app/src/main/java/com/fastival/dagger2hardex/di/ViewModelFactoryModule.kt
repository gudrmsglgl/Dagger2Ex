package com.fastival.dagger2hardex.di

import androidx.lifecycle.ViewModelProvider
import com.fastival.dagger2hardex.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}