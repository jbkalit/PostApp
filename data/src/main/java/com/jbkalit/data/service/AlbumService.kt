package com.jbkalit.data.service

import com.jbkalit.domain.model.Album
import com.jbkalit.domain.model.Photo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {

    @GET("albums")
    fun getAlbumByUserId(@Query("userId") userId: Int): Single<List<Album>>

    @GET("photos")
    fun getPhotoByAlbumId(@Query("albumId") albumId: Int): Single<List<Photo>>

}
