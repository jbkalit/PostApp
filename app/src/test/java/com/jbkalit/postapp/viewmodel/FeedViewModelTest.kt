package com.jbkalit.postapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.domain.model.Feed
import com.jbkalit.domain.usecase.feed.FeedUseCaseContract
import com.jbkalit.postapp.mock.feed
import com.jbkalit.postapp.presentation.feed.viewmodel.FeedViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class FeedViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var testFeed: Single<List<Feed>>? = null

    @Mock
    private lateinit var feedUseCase: FeedUseCaseContract

    private lateinit var feedViewModel: FeedViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        feedViewModel = FeedViewModel(feedUseCase)
    }

    @Test
    fun getFeed_Success_Test() {
        testFeed = Single.just(listOf(feed))
        `when`(feedUseCase.getAllPosts()).thenReturn(testFeed)
        feedViewModel.fetchFeed()
        Assert.assertEquals(1, feedViewModel.feed.value?.size)
        Assert.assertEquals(false, feedViewModel.isError.value)
        Assert.assertEquals(false, feedViewModel.isLoading.value)
    }

    @Test
    fun getFeed_ErrorShow_Test() {
        testFeed = Single.error(Throwable())
        `when`(feedUseCase.getAllPosts()).thenReturn(testFeed)
        feedViewModel.fetchFeed()
        Assert.assertEquals(true, feedViewModel.isError.value)
    }

    @Test
    fun getFeed_LoadingShow_Test() {
        testFeed = Single.never()
        `when`(feedUseCase.getAllPosts()).thenReturn(testFeed)
        feedViewModel.fetchFeed()
        Assert.assertEquals(true, feedViewModel.isLoading.value)
    }

}
