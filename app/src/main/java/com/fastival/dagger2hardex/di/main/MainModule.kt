package com.fastival.dagger2hardex.di.main

import android.app.Application
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastival.dagger2hardex.network.main.MainApi
import com.fastival.dagger2hardex.ui.main.posts.PostRcvAdapter
import com.fastival.dagger2hardex.util.VerticalSpaceItemDecoration
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Provider

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }


}