package com.jbkalit.postapp.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.jbkalit.postapp.databinding.FragmentProfileBinding
import com.jbkalit.postapp.presentation.user.adapter.AlbumAdapter
import com.jbkalit.postapp.presentation.user.adapter.PhotoAdapter
import com.jbkalit.postapp.presentation.user.viewmodel.ProfileViewModel
import com.jbkalit.postapp.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(), PhotoAdapter.OnPhotoClickListener {

    private val profileViewModel: ProfileViewModel by viewModels()
    private val args: ProfileFragmentArgs by navArgs()

    private lateinit var binding: FragmentProfileBinding
    private lateinit var albumAdapter: AlbumAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setupObserver()
        profileViewModel.fetchProfile(args.userId)
        setupAlbumObserver()
        profileViewModel.fetchAlbums(args.userId)
    }

    private fun setUpView() {
        with(binding) {
            albumRecyclerView.layoutManager = LinearLayoutManager(activity)
            albumAdapter = AlbumAdapter(mutableListOf(), this@ProfileFragment)
            albumRecyclerView.adapter = albumAdapter
        }
    }

    private fun setupObserver() {
        profileViewModel.user.observe(viewLifecycleOwner, Observer { response ->
            response?.let { user ->
                binding.nameTextView.text = user.name
                binding.usernameTextView.text = user.username
                binding.emailTextView.text = user.email
                binding.addressTextView.text =
                    user.address.street.toString() + ", " + user.address.city.toString()
                binding.companyTextView.text = user.company.name
            }
        })
    }

    private fun setupAlbumObserver() {
        with(binding) {
            profileViewModel.albums.observe(viewLifecycleOwner, Observer { response ->
                response?.let {
                    contentLayout.visibility = View.VISIBLE
                    albumRecyclerView.visibility = View.VISIBLE
                    albumAdapter.addToAlbumList(it.toMutableList())
                }
            })
            profileViewModel.isLoading.observe(viewLifecycleOwner, Observer { response ->
                response?.let {
                    loadingLayout.root.visibility = if (it) View.VISIBLE else View.GONE
                    if (it) {
                        albumRecyclerView.visibility = View.GONE
                        errorLayout.root.visibility = View.GONE
                    }
                }
            })
            profileViewModel.isError.observe(viewLifecycleOwner, Observer { response ->
                response?.let {
                    errorLayout.root.visibility = if (it) View.VISIBLE else View.GONE
                    if (it) {
                        contentLayout.visibility = View.GONE
                        loadingLayout.root.visibility = View.GONE
                    }
                }
            })
        }
    }

    override fun onPhotoClicked(url: String, title: String) {
        navigate(ProfileFragmentDirections.actionProfileFragmentToPhotoFragment(url, title))
    }

}
