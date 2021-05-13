package com.jbkalit.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.scheduler.SchedulerProvider
import com.jbkalit.data.service.UserService
import com.jbkalit.data.source.user.remote.UserRemoteDataSource
import com.jbkalit.data.source.user.remote.UserRemoteDataSourceContract
import com.jbkalit.data.mock.user
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserRemoteDataSourceTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var userService: UserService

    private lateinit var schedulerProvider: BaseSchedulerProvider
    private lateinit var userRemoteDataSource: UserRemoteDataSourceContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        schedulerProvider = SchedulerProvider.getInstance()

        userRemoteDataSource = UserRemoteDataSource(
            userService,
            schedulerProvider
        )
    }

    @Test
    fun getUserById_Success_Test() {
        Mockito.`when`(userService.getUserById(1))
            .thenReturn(Single.just(user))

        userRemoteDataSource.getUserById(1)

        Mockito.verify(userService, Mockito.times(1)).getUserById(1)
        Assert.assertNotNull("User not empty", userRemoteDataSource.getUserById(1))
    }

}
