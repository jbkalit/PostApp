package com.jbkalit.postapp.presentation.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.jbkalit.postapp.databinding.FragmentPhotoBinding
import okhttp3.internal.userAgent

class PhotoFragment: Fragment() {

    private val args: PhotoFragmentArgs by navArgs()

    private lateinit var binding : FragmentPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(args.url, args.title)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    private fun setUpView(url: String, title: String) {
        with(binding) {
            titleTextView.text = title
            val glideUrl = GlideUrl(url, LazyHeaders.Builder()
                    .addHeader("User-Agent", userAgent)
                    .build()
            )
            Glide.with(this@PhotoFragment)
                .asBitmap()
                .load(glideUrl)
                .into(detailPhotoImageView)
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

}
