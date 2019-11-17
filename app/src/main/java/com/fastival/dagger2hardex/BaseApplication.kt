package com.fastival.dagger2hardex

import com.fastival.dagger2hardex.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
         return DaggerAppComponent.builder().application(this).build()
    }

}