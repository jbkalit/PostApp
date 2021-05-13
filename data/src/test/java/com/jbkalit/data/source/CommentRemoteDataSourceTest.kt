package com.jbkalit.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.scheduler.SchedulerProvider
import com.jbkalit.data.service.CommentService
import com.jbkalit.data.source.comment.remote.CommentRemoteDataSource
import com.jbkalit.data.source.comment.remote.CommentRemoteDataSourceContract
import com.jbkalit.data.mock.comment
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CommentRemoteDataSourceTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var commentService: CommentService

    private lateinit var schedulerProvider: BaseSchedulerProvider
    private lateinit var commentRemoteDataSource: CommentRemoteDataSourceContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        schedulerProvider = SchedulerProvider.getInstance()

        commentRemoteDataSource = CommentRemoteDataSource(
            commentService,
            schedulerProvider
        )
    }

    @Test
    fun getPostById_Success_Test() {
        Mockito.`when`(commentService.getCommentsByPostId(1))
            .thenReturn(Single.just(listOf(comment)))

        commentRemoteDataSource.getCommentsByPostId(1)

        Mockito.verify(commentService, Mockito.times(1)).getCommentsByPostId(1)
        Assert.assertNotNull("Comment not empty", commentRemoteDataSource.getCommentsByPostId(1))
    }

}
