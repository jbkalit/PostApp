package com.jbkalit.domain.usecase.feed

import com.jbkalit.domain.model.Feed
import io.reactivex.Single

interface FeedUseCaseContract {

    fun getAllPosts() : Single<List<Feed>>

}
