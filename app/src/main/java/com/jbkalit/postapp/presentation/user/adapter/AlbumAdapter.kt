package com.jbkalit.postapp.presentation.user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jbkalit.domain.model.Album
import com.jbkalit.postapp.databinding.ViewAlbumItemBinding

class AlbumAdapter(private var albumList: MutableList<Album>,
                   private val listener: PhotoAdapter.OnPhotoClickListener)
    : ListAdapter<Album, RecyclerView.ViewHolder>(NewsResponseItemDiffCallback()) {

    private lateinit var binding: ViewAlbumItemBinding
    private lateinit var adapter: PhotoAdapter

    override fun getItemCount() = albumList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ViewAlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        setUpView(holder.itemView.context)
        (holder as ViewHolder).bind(albumList[position])
    }

    private fun setUpView(context: Context) {
        with(binding) {
            photoRecyclerView.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = PhotoAdapter(context, mutableListOf(), listener)
            photoRecyclerView.adapter = adapter
        }
    }

    inner class ViewHolder(private val binding: ViewAlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            with(binding) {
                titleTextView.text = album.title
                adapter.addToPhotoList(album.photoList.toMutableList())
            }
        }
    }

    class NewsResponseItemDiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }
    }

    fun addToAlbumList(albumList: MutableList<Album>) {
        this.albumList.clear()
        this.albumList.addAll(albumList)
        notifyDataSetChanged()
    }

}
