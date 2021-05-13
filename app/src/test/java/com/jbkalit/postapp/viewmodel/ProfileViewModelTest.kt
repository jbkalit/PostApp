package com.jbkalit.postapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.domain.model.Album
import com.jbkalit.domain.model.User
import com.jbkalit.domain.usecase.album.AlbumUseCaseContract
import com.jbkalit.domain.usecase.user.UserUseCaseContract
import com.jbkalit.postapp.mock.user
import com.jbkalit.postapp.presentation.user.viewmodel.ProfileViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ProfileViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var testUser: Single<User>? = null

    private var testAlbum: Single<List<Album>>? = null

    @Mock
    private lateinit var userUseCase: UserUseCaseContract

    @Mock
    private lateinit var albumUseCase: AlbumUseCaseContract

    private lateinit var profileViewModel: ProfileViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        profileViewModel = ProfileViewModel(userUseCase, albumUseCase)
    }

    @Test
    fun getProfile_Success_Test() {
        testUser = Single.just(user)
        `when`(userUseCase.getUserById(1)).thenReturn(testUser)
        profileViewModel.fetchProfile(1)
        Assert.assertEquals(user, profileViewModel.user.value)
        Assert.assertEquals(false, profileViewModel.isError.value)
    }

    @Test
    fun getProfile_ErrorShow_Test() {
        testUser = Single.error(Throwable())
        `when`(userUseCase.getUserById(1)).thenReturn(testUser)
        profileViewModel.fetchProfile(1)
        Assert.assertEquals(true, profileViewModel.isError.value)
    }

    @Test
    fun getProfile_LoadingShow_Test() {
        testAlbum = Single.never()
        `when`(albumUseCase.getAlbumByUserId(1)).thenReturn(testAlbum)
        profileViewModel.fetchAlbums(1)
        Assert.assertEquals(true, profileViewModel.isLoading.value)
    }

}
