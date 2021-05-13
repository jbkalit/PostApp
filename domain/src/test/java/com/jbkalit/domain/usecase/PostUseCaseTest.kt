package com.jbkalit.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.domain.repository.post.PostRepositoryContract
import com.jbkalit.domain.usecase.post.PostUseCase
import com.jbkalit.domain.usecase.post.PostUseCaseContract
import com.jbkalit.domain.mock.postDetail
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PostUseCaseTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var postRepository: PostRepositoryContract

    private lateinit var postUseCase: PostUseCaseContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        postUseCase = PostUseCase(postRepository)
    }

    @Test
    fun getPostById_Success_Test() {
        Mockito.`when`(postRepository.getPostById(1))
            .thenReturn(Single.just(postDetail))

        postUseCase.getPostById(1)

        Mockito.verify(postRepository, Mockito.times(1)).getPostById(1)
        Assert.assertNotNull("Post not empty", postUseCase.getPostById(1))
    }

}
