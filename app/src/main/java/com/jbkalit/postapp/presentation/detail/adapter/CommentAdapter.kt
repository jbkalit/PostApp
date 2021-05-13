package com.jbkalit.postapp.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jbkalit.domain.model.Comment
import com.jbkalit.postapp.databinding.ViewCommentItemBinding

class CommentAdapter(private var commentList : MutableList<Comment>)
    : ListAdapter<Comment, RecyclerView.ViewHolder>(NewsResponseItemDiffCallback()) {

    override fun getItemCount() = commentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewCommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(commentList[position])
    }

    inner class ViewHolder(private val binding: ViewCommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            with(binding) {
                usernameTextView.text = comment.email
                bodyTextView.text = comment.body
            }
        }
    }

    class NewsResponseItemDiffCallback : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }
    }

    fun addToList(commentList: MutableList<Comment>) {
        this.commentList.clear()
        this.commentList.addAll(commentList)
        notifyDataSetChanged()
    }

}
