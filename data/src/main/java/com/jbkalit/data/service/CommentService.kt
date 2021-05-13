package com.jbkalit.data.service

import com.jbkalit.domain.model.Comment
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {

    @GET("comments")
    fun getCommentsByPostId(@Query("postId") postId : Int) : Single<List<Comment>>

}
