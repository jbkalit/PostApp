package com.jbkalit.domain.usecase.album

import com.jbkalit.domain.model.Album
import com.jbkalit.domain.model.Photo
import io.reactivex.Single

interface AlbumUseCaseContract {

    fun getAlbumByUserId(userId: Int): Single<List<Album>>

    fun getPhotoByAlbumId(albumId: Int): Single<List<Photo>>

}
