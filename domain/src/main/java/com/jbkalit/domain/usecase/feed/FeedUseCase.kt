package com.jbkalit.domain.usecase.feed

import com.jbkalit.domain.model.Feed
import com.jbkalit.domain.repository.post.PostRepositoryContract
import io.reactivex.Single
import javax.inject.Inject

class FeedUseCase @Inject
constructor(private val postRepository: PostRepositoryContract) : FeedUseCaseContract {

    override fun getAllPosts() : Single<List<Feed>> {
        return postRepository.getAllPosts()
    }

}
