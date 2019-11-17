package com.fastival.dagger2hardex.di.main

import com.fastival.dagger2hardex.di.main.posts.FragScope
import com.fastival.dagger2hardex.di.main.posts.PostModule
import com.fastival.dagger2hardex.ui.main.posts.PostsFragment
import com.fastival.dagger2hardex.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @FragScope
    @ContributesAndroidInjector(
        modules = [PostModule::class]
    )
    abstract fun contributePostFragment(): PostsFragment
}