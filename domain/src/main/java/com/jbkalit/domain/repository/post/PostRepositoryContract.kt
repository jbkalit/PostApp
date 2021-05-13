package com.jbkalit.domain.repository.post

import com.jbkalit.domain.model.*
import io.reactivex.Single

interface PostRepositoryContract {

    fun getUserById(userId: Int): Single<User>

    fun getAllPosts(): Single<List<Feed>>

    fun getPostById(postId: Int): Single<PostDetail>

    fun getCommentsByPostId(postId: Int): Single<List<Comment>>

}
