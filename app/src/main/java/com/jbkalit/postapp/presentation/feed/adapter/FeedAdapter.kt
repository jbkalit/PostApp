package com.jbkalit.postapp.presentation.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jbkalit.domain.model.Feed
import com.jbkalit.postapp.databinding.ViewFeedItemBinding

class FeedAdapter(private var feed : MutableList<Feed>,
                  private val feedItemListener: OnFeedClickListener) :
    ListAdapter<Feed, RecyclerView.ViewHolder>(NewsResponseItemDiffCallback()) {

    override fun getItemCount() = feed.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewFeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(feed[position])
    }

    inner class ViewHolder(private val binding: ViewFeedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(feed: Feed) {
            with(binding) {
                titleTextView.text = feed.title
                usernameTextView.text = feed.username
                companyTextView.text = feed.company_name
                bodyTextView.text = feed.body
                itemView.setOnClickListener {
                    feedItemListener.onFeedClicked(feed)
                }
            }
        }
    }

    class NewsResponseItemDiffCallback : DiffUtil.ItemCallback<Feed>() {
        override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem == newItem
        }
    }

    fun addToList(feedList: MutableList<Feed>) {
        this.feed.clear()
        this.feed.addAll(feedList)
        notifyDataSetChanged()
    }

    interface OnFeedClickListener {
        fun onFeedClicked(feed: Feed)
    }

}
