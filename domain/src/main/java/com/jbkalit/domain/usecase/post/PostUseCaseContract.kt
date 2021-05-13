package com.jbkalit.domain.usecase.post

import com.jbkalit.domain.model.PostDetail
import io.reactivex.Single

interface PostUseCaseContract {

    fun getPostById(postId: Int): Single<PostDetail>

}
