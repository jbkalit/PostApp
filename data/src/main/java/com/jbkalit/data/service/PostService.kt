package com.jbkalit.data.service

import com.jbkalit.domain.model.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {

    @GET("posts")
    fun getAllPosts() : Single<List<Post>>

    @GET("posts")
    fun getPostsByUserId(@Query("userId") userId : Int) : Single<List<Post>>

    @GET("posts/{id}")
    fun getPostById(@Path("id") postId: Int): Single<Post>

}
