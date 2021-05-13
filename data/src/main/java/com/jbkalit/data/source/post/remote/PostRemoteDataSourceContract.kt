package com.jbkalit.data.source.post.remote

import com.jbkalit.domain.model.Post
import io.reactivex.Single

interface PostRemoteDataSourceContract {

    fun getAllPosts(): Single<List<Post>>

    fun getPostById(postId: Int): Single<Post>

}
