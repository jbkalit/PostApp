package com.jbkalit.data.source.post.remote

import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.service.PostService
import com.jbkalit.domain.model.Post
import io.reactivex.Single
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(private val postService: PostService,
                                               private val schedulerProvider: BaseSchedulerProvider
)
    : PostRemoteDataSourceContract {

    override fun getAllPosts(): Single<List<Post>> {
        return postService.getAllPosts()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

    override fun getPostById(postId: Int): Single<Post> {
        return postService.getPostById(postId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

}
