package com.jbkalit.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.domain.repository.user.UserRepositoryContract
import com.jbkalit.domain.usecase.user.UserUseCase
import com.jbkalit.domain.usecase.user.UserUseCaseContract
import com.jbkalit.domain.mock.user
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserUseCaseTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var userRepository: UserRepositoryContract

    private lateinit var userUseCase: UserUseCaseContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        userUseCase = UserUseCase(userRepository)
    }

    @Test
    fun getUserById_Success_Test() {
        Mockito.`when`(userRepository.getUserById(1))
            .thenReturn(Single.just(user))

        userUseCase.getUserById(1)

        Mockito.verify(userRepository, Mockito.times(1)).getUserById(1)
        Assert.assertNotNull("User not empty", userUseCase.getUserById(1))
    }

}
