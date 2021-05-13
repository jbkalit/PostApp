package com.jbkalit.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.data.repository.post.PostRepository
import com.jbkalit.data.source.comment.remote.CommentRemoteDataSourceContract
import com.jbkalit.data.source.post.remote.PostRemoteDataSourceContract
import com.jbkalit.data.source.user.remote.UserRemoteDataSourceContract
import com.jbkalit.domain.repository.post.PostRepositoryContract
import com.jbkalit.data.mock.comment
import com.jbkalit.data.mock.post
import com.jbkalit.data.mock.user
import io.reactivex.Single.just
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class PostRepositoryTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var postRemoteDataSource: PostRemoteDataSourceContract
    @Mock private lateinit var userRemoteDataSource: UserRemoteDataSourceContract
    @Mock private lateinit var commentRemoteDataSource: CommentRemoteDataSourceContract

    private lateinit var postRepository: PostRepositoryContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        postRepository = PostRepository(
            postRemoteDataSource,
            commentRemoteDataSource,
            userRemoteDataSource
        )
    }

    @Test
    fun getUserByUserId_Success_Test() {
        whenever(userRemoteDataSource.getUserById(1))
            .thenReturn(just(user))

        postRepository.getUserById(1)

        verify(userRemoteDataSource, times(1)).getUserById(1)
        assertNotNull("User not empty", postRepository.getUserById(1))
    }

    @Test
    fun getFeed_Success_Test() {
        whenever(postRemoteDataSource.getAllPosts())
            .thenReturn(just(listOf(post)))

        postRepository.getAllPosts()

        verify(postRemoteDataSource, times(1)).getAllPosts()
        assertNotNull("Feed not empty", postRepository.getAllPosts())
    }

    @Test
    fun getCommentByPostId_Success_Test() {
        whenever(commentRemoteDataSource.getCommentsByPostId(1))
            .thenReturn(just(listOf(comment)))

        postRepository.getCommentsByPostId(1)

        verify(commentRemoteDataSource, times(1)).getCommentsByPostId(1)
        assertNotNull("Comment not empty", postRepository.getCommentsByPostId(1))
    }

}
