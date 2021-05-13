package com.jbkalit.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.domain.repository.post.PostRepositoryContract
import com.jbkalit.domain.usecase.feed.FeedUseCase
import com.jbkalit.domain.usecase.feed.FeedUseCaseContract
import com.jbkalit.domain.mock.feed
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FeedUseCaseTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var postRepository: PostRepositoryContract

    private lateinit var feedUseCase: FeedUseCaseContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        feedUseCase = FeedUseCase(postRepository)
    }

    @Test
    fun getFeed_Success_Test() {
        Mockito.`when`(postRepository.getAllPosts())
            .thenReturn(Single.just(listOf(feed)))

        feedUseCase.getAllPosts()

        Mockito.verify(postRepository, Mockito.times(1)).getAllPosts()
        Assert.assertNotNull("Feed not empty", feedUseCase.getAllPosts())
    }

}
