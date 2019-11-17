package com.fastival.dagger2hardex.di.main.posts

import android.app.Application
import androidx.recyclerview.widget.LinearLayoutManager
import com.fastival.dagger2hardex.di.main.MainScope
import com.fastival.dagger2hardex.ui.main.posts.PostRcvAdapter
import com.fastival.dagger2hardex.util.VerticalSpaceItemDecoration
import dagger.Module
import dagger.Provides

@Module
class PostModule {

    @FragScope
    @Provides
    fun providePostAdapter(): PostRcvAdapter {
        return PostRcvAdapter()
    }

    @FragScope
    @Provides
    fun provideLayoutManagerLi(application: Application): LinearLayoutManager {
        return LinearLayoutManager(application)
    }

    @FragScope
    @Provides
    fun provideItemDecoration(): VerticalSpaceItemDecoration {
        return VerticalSpaceItemDecoration(15)
    }
}