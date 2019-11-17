package com.fastival.dagger2hardex.ui.main.posts

import android.util.Log
import androidx.lifecycle.*
import com.fastival.dagger2hardex.SessionManager
import com.fastival.dagger2hardex.models.Post
import com.fastival.dagger2hardex.models.Resource
import com.fastival.dagger2hardex.network.main.MainApi
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import javax.inject.Inject

class PostViewModel: ViewModel {

    private val refSessionManager: WeakReference<SessionManager>
    private val mainApi: MainApi

    private val posts: MediatorLiveData<Resource<List<Post>>>

    @Inject
    constructor(sessionManager: SessionManager, mainApi: MainApi){
        this.refSessionManager = WeakReference(sessionManager)
        this.mainApi = mainApi
        posts = MediatorLiveData()

        Log.d("Main", "postsViewModel: viewmodel is working ... ")
    }

    fun getPosts(): LiveData<Resource<List<Post>>> {

        val source = LiveDataReactiveStreams.fromPublisher(
            mainApi.getPostsFromUser(getUserId())
                .onErrorReturn {
                    val post = Post(-1)
                    val list = listOf(post)
                    list
                }
                .map {
                    if (it.get(0).id == -1) Resource.Error("Something is wrong", null)
                    else Resource.Success(it)
                }
                .subscribeOn(Schedulers.io())
        )

        posts.addSource(source, Observer {
            posts.value = it
            posts.removeSource(source)
        })

        return posts
    }

    private fun getUserId() = refSessionManager.get()!!.getAuthUser().value!!.data!!.id

}