package com.jbkalit.postapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.jbkalit.postapp.databinding.FragmentDetailPostBinding
import com.jbkalit.postapp.presentation.detail.adapter.CommentAdapter
import com.jbkalit.postapp.presentation.detail.viewmodel.DetailPostViewModel
import com.jbkalit.postapp.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPostFragment : Fragment() {

    private val detailPostViewModel : DetailPostViewModel by viewModels()
    private val args : DetailPostFragmentArgs by navArgs()

    private lateinit var binding: FragmentDetailPostBinding
    private lateinit var adapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentDetailPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setupObserver()
        setupCommentObserver()
        detailPostViewModel.fetchDetailPost(args.postId)
    }

    private fun setUpView() {
        with(binding) {
            commentRecyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = CommentAdapter(mutableListOf())
            commentRecyclerView.adapter = adapter
        }
    }

    private fun setupObserver() {
        with(binding) {
            detailPostViewModel.post.observe(viewLifecycleOwner, Observer { response ->
                response?.let { postDetail ->
                    contentLayout.visibility = View.VISIBLE
                    binding.titleTextView.text = postDetail.title
                    binding.nameTextView.text = postDetail.name
                    binding.usernameTextView.text = postDetail.username
                    binding.bodyTextView.text = postDetail.body
                    usernameTextView.setOnClickListener {
                        onUsernameClicked(postDetail.userId)
                    }
                    nameTextView.setOnClickListener {
                        onUsernameClicked(postDetail.userId)
                    }
                }
            })
            detailPostViewModel.isLoading.observe(viewLifecycleOwner, Observer { response ->
                response?.let {
                    loadingLayout.root.visibility = if (it) View.VISIBLE else View.GONE
                    if (it) {
                        contentLayout.visibility = View.GONE
                        errorLayout.root.visibility = View.GONE
                    }
                }
            })
            detailPostViewModel.isError.observe(viewLifecycleOwner, Observer { response ->
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

    private fun setupCommentObserver() {
        detailPostViewModel.post.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                adapter.addToList(it.commentList.toMutableList())
            }
        })
    }

    private fun onUsernameClicked(userId: Int) {
        navigate(DetailPostFragmentDirections.actionDetailPostFragmentToProfileFragment(userId))
    }

}
