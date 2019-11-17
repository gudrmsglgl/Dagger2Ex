package com.fastival.dagger2hardex.di.main

import androidx.lifecycle.ViewModel
import com.fastival.dagger2hardex.di.ViewModelKey
import com.fastival.dagger2hardex.ui.main.posts.PostViewModel
import com.fastival.dagger2hardex.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(viewModel: PostViewModel) : ViewModel
}