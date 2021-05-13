package com.jbkalit.data.source.comment.remote

import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.service.CommentService
import com.jbkalit.domain.model.Comment
import io.reactivex.Single
import javax.inject.Inject

class CommentRemoteDataSource @Inject constructor(private val commentService: CommentService,
                                                  private val schedulerProvider: BaseSchedulerProvider
)
    : CommentRemoteDataSourceContract {

    override fun getCommentsByPostId(postId: Int): Single<List<Comment>> {
        return commentService.getCommentsByPostId(postId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

}
