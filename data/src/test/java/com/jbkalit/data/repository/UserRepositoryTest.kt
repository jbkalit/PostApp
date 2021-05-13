package com.jbkalit.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.data.repository.user.UserRepository
import com.jbkalit.data.source.user.remote.UserRemoteDataSourceContract
import com.jbkalit.domain.repository.user.UserRepositoryContract
import com.jbkalit.data.mock.user
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserRepositoryTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var userRemoteDataSource: UserRemoteDataSourceContract

    private lateinit var userRepository: UserRepositoryContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(userRemoteDataSource)
    }

    @Test
    fun getUserByUserId_Success_Test() {
        Mockito.`when`(userRemoteDataSource.getUserById(1))
            .thenReturn(Single.just(user))

        userRepository.getUserById(1)

        Mockito.verify(userRemoteDataSource, Mockito.times(1)).getUserById(1)
        Assert.assertNotNull("User not empty", userRepository.getUserById(1))
    }

}
