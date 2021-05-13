package com.jbkalit.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.domain.repository.album.AlbumRepositoryContract
import com.jbkalit.domain.usecase.album.AlbumUseCase
import com.jbkalit.domain.usecase.album.AlbumUseCaseContract
import com.jbkalit.domain.mock.album
import com.jbkalit.domain.mock.photo
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AlbumUseCaseTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var albumRepository: AlbumRepositoryContract

    private lateinit var albumUseCase: AlbumUseCaseContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        albumUseCase = AlbumUseCase(albumRepository)
    }

    @Test
    fun getAlbumByUserId_Success_Test() {
        Mockito.`when`(albumRepository.getAlbumByUserId(1))
            .thenReturn(Single.just(listOf(album)))

        albumUseCase.getAlbumByUserId(1)

        Mockito.verify(albumRepository, Mockito.times(1)).getAlbumByUserId(1)
        Assert.assertNotNull("Album not empty", albumUseCase.getAlbumByUserId(1))
    }

    @Test
    fun getPostById_Success_Test() {
        Mockito.`when`(albumRepository.getPhotoByAlbumId(1))
            .thenReturn(Single.just(listOf(photo)))

        albumUseCase.getPhotoByAlbumId(1)

        Mockito.verify(albumRepository, Mockito.times(1)).getPhotoByAlbumId(1)
        Assert.assertNotNull("Photo not empty", albumUseCase.getPhotoByAlbumId(1))
    }

}
