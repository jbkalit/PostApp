package com.jbkalit.data.source.album.remote

import com.jbkalit.domain.model.Album
import com.jbkalit.domain.model.Photo
import io.reactivex.Single

interface AlbumRemoteDataSourceContract {

    fun getAlbumByUserId(userId: Int): Single<List<Album>>

    fun getPhotoByAlbumId(albumId: Int): Single<List<Photo>>

}
