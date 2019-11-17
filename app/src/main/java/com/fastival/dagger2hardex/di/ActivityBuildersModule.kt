package com.fastival.dagger2hardex.di

import com.fastival.dagger2hardex.di.auth.AuthModule
import com.fastival.dagger2hardex.di.auth.AuthScope
import com.fastival.dagger2hardex.di.auth.AuthViewModelModule
import com.fastival.dagger2hardex.di.main.MainFragmentBuildersModule
import com.fastival.dagger2hardex.di.main.MainModule
import com.fastival.dagger2hardex.di.main.MainScope
import com.fastival.dagger2hardex.di.main.MainViewModelModule
import com.fastival.dagger2hardex.ui.auth.AuthActivity
import com.fastival.dagger2hardex.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector (
        modules = [AuthViewModelModule::class, AuthModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector (
        modules = [MainFragmentBuildersModule::class, MainViewModelModule::class, MainModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}