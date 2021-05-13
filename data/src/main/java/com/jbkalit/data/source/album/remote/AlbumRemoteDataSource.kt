package com.jbkalit.data.source.album.remote

import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.service.AlbumService
import com.jbkalit.domain.model.Album
import com.jbkalit.domain.model.Photo
import io.reactivex.Single
import javax.inject.Inject

class AlbumRemoteDataSource @Inject constructor(private val albumService: AlbumService,
                                                private val schedulerProvider: BaseSchedulerProvider
)
    : AlbumRemoteDataSourceContract {

    override fun getAlbumByUserId(userId: Int): Single<List<Album>> {
        return albumService.getAlbumByUserId(userId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

    override fun getPhotoByAlbumId(albumId: Int): Single<List<Photo>> {
        return albumService.getPhotoByAlbumId(albumId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

}
