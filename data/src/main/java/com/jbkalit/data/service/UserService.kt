package com.jbkalit.data.service

import com.jbkalit.domain.model.Album
import com.jbkalit.domain.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("users/{id}")
    fun getUserById(@Path("id") userId: Int): Single<User>

    @GET("albums")
    fun getAlbumByUserId(@Path("userId") userId: Int): Single<List<Album>>

}
