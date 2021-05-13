package com.jbkalit.data.source.comment.remote

import com.jbkalit.domain.model.Comment
import io.reactivex.Single

interface CommentRemoteDataSourceContract {

    fun getCommentsByPostId(postId: Int): Single<List<Comment>>

}
