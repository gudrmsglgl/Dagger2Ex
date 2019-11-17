package com.fastival.dagger2hardex.di

import android.app.Application
import com.fastival.dagger2hardex.BaseApplication
import com.fastival.dagger2hardex.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityBuildersModule::class, AppModule::class
    , ViewModelFactoryModule::class]
)
interface AppComponent: AndroidInjector<BaseApplication> {

    fun sessionManager(): SessionManager
    
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}