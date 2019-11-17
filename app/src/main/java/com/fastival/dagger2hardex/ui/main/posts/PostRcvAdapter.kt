package com.fastival.dagger2hardex.ui.main.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fastival.dagger2hardex.R
import com.fastival.dagger2hardex.models.Post
import kotlinx.android.synthetic.main.layout_post_list_item.view.*

class PostRcvAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Post> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_post_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostViewHolder -> holder.bind(items[position])
        }
    }

    fun setPosts(posts: List<Post>) {
        this.items = posts
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(post: Post) {
            itemView.title.text = post.title
        }
    }

}