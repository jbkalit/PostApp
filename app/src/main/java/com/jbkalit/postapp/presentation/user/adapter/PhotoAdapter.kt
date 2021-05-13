package com.jbkalit.postapp.presentation.user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.jbkalit.domain.model.Photo
import com.jbkalit.postapp.databinding.ViewPhotoItemBinding
import okhttp3.internal.userAgent

class PhotoAdapter(val context: Context,
                   private var photoList: MutableList<Photo>,
                   private val photoItemListener: OnPhotoClickListener)
    : ListAdapter<Photo, RecyclerView.ViewHolder>(NewsResponseItemDiffCallback()) {

    override fun getItemCount() = photoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(photoList[position])
    }

    inner class ViewHolder(private val binding: ViewPhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            with(binding) {
                val url = GlideUrl(
                    photo.thumbnailUrl, LazyHeaders.Builder()
                        .addHeader("User-Agent", userAgent)
                        .build()
                )
                Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .into(photoImageView)
                itemView.setOnClickListener {
                    photo.url?.let { url ->
                        photo.title?.let { title ->
                            photoItemListener.onPhotoClicked(url, title)
                        }
                    }
                }
            }
        }
    }

    class NewsResponseItemDiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

    fun addToPhotoList(albumList: MutableList<Photo>) {
        this.photoList.clear()
        this.photoList.addAll(albumList)
        notifyDataSetChanged()
    }

    interface OnPhotoClickListener {
        fun onPhotoClicked(url: String, title: String)
    }

}
