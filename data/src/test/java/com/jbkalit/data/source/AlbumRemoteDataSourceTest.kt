package com.jbkalit.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.scheduler.SchedulerProvider
import com.jbkalit.data.service.AlbumService
import com.jbkalit.data.source.album.remote.AlbumRemoteDataSource
import com.jbkalit.data.source.album.remote.AlbumRemoteDataSourceContract
import com.jbkalit.data.mock.album
import com.jbkalit.data.mock.photo
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AlbumRemoteDataSourceTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var albumService: AlbumService

    private lateinit var schedulerProvider: BaseSchedulerProvider
    private lateinit var albumRemoteDataSource: AlbumRemoteDataSourceContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        schedulerProvider = SchedulerProvider.getInstance()

        albumRemoteDataSource = AlbumRemoteDataSource(
            albumService,
            schedulerProvider
        )
    }

    @Test
    fun getAlbumByUserId_Success_Test() {
        Mockito.`when`(albumService.getAlbumByUserId(1))
            .thenReturn(Single.just(listOf(album)))

        albumRemoteDataSource.getAlbumByUserId(1)

        Mockito.verify(albumService, Mockito.times(1)).getAlbumByUserId(1)
        Assert.assertNotNull("Album not empty", albumRemoteDataSource.getAlbumByUserId(1))
    }

    @Test
    fun getPostById_Success_Test() {
        Mockito.`when`(albumService.getPhotoByAlbumId(1))
            .thenReturn(Single.just(listOf(photo)))

        albumRemoteDataSource.getPhotoByAlbumId(1)

        Mockito.verify(albumService, Mockito.times(1)).getPhotoByAlbumId(1)
        Assert.assertNotNull("Photo not empty", albumRemoteDataSource.getPhotoByAlbumId(1))
    }

}
