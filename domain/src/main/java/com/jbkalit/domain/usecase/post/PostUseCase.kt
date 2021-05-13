package com.jbkalit.domain.usecase.post

import com.jbkalit.domain.repository.post.PostRepositoryContract
import javax.inject.Inject

class PostUseCase @Inject constructor(private val postRepository: PostRepositoryContract)
    : PostUseCaseContract {

    override fun getPostById(postId: Int) = postRepository.getPostById(postId)

}
