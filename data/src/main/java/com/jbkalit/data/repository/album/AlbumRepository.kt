package com.jbkalit.data.repository.album


import com.jbkalit.data.source.album.remote.AlbumRemoteDataSourceContract
import com.jbkalit.domain.model.*
import com.jbkalit.domain.repository.album.AlbumRepositoryContract
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRepository @Inject
constructor(private val albumRemoteDataSource: AlbumRemoteDataSourceContract)
    : AlbumRepositoryContract {

    override fun getAlbumByUserId(userId: Int): Single<List<Album>> {
        return albumRemoteDataSource.getAlbumByUserId(userId)
            .toObservable()
            .flatMapIterable { it }
            .concatMap { album ->
                return@concatMap getPhotoByAlbumId(album.id!!)
                    .toObservable()
                    .flatMap { photos ->
                        return@flatMap Single.just(
                            Album (
                                userId = album.userId!!,
                                id = album.id!!,
                                title = album.title,
                                photoList = photos
                            )).toObservable()
                    }
            }.toList()
            .flatMap {
                return@flatMap Single.just(it)
            }
    }

    override fun getPhotoByAlbumId(albumId: Int): Single<List<Photo>> {
        return albumRemoteDataSource.getPhotoByAlbumId(albumId)
    }

}
