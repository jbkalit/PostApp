package com.jbkalit.domain.repository.album

import com.jbkalit.domain.model.Album
import com.jbkalit.domain.model.Photo
import io.reactivex.Single

interface AlbumRepositoryContract {

    fun getAlbumByUserId(userId: Int): Single<List<Album>>

    fun getPhotoByAlbumId(albumId: Int): Single<List<Photo>>

}
