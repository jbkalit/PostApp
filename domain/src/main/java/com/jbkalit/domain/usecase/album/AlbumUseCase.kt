package com.jbkalit.domain.usecase.album

import com.jbkalit.domain.repository.album.AlbumRepositoryContract
import javax.inject.Inject

class AlbumUseCase @Inject constructor(private val albumRepository: AlbumRepositoryContract)
    : AlbumUseCaseContract {

    override fun getAlbumByUserId(userId: Int) = albumRepository.getAlbumByUserId(userId)

    override fun getPhotoByAlbumId(albumId: Int) = albumRepository.getPhotoByAlbumId(albumId)

}
