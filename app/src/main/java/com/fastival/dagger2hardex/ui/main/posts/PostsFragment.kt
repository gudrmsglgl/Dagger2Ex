package com.fastival.dagger2hardex.ui.main.posts


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.fastival.dagger2hardex.R
import com.fastival.dagger2hardex.models.Resource
import com.fastival.dagger2hardex.util.VerticalSpaceItemDecoration
import com.fastival.dagger2hardex.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject
import javax.inject.Provider

/**
 * A simple [Fragment] subclass.
 */
class PostsFragment : DaggerFragment() {

    @Inject
    lateinit var provider: ViewModelProviderFactory

    lateinit var viewModel: PostViewModel

    @Inject
    lateinit var postAdapter: PostRcvAdapter

    @Inject
    lateinit var liManager: LinearLayoutManager

    @Inject
    lateinit var itemDecoration: VerticalSpaceItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            viewModel = ViewModelProviders.of(this, provider).get(PostViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        viewModel.getPosts().observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Loading -> Log.d("Main", "onChanged: PostsFragment: LOADING ...")
                is Resource.Success -> {
                    Log.d("Main", "onChanged: PostsFragment: got posts. ...")
                    postAdapter.setPosts(it.data!!)
                }
                is Resource.Error -> Log.d("Main","error ${it.message}")
            }
        })
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = liManager
            adapter = postAdapter
            addItemDecoration(itemDecoration)
        }
    }
}
