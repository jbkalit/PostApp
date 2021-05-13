package com.jbkalit.data.repository.post

import com.jbkalit.data.source.comment.remote.CommentRemoteDataSourceContract
import com.jbkalit.data.source.post.remote.PostRemoteDataSourceContract
import com.jbkalit.data.source.user.remote.UserRemoteDataSourceContract
import com.jbkalit.domain.model.*
import com.jbkalit.domain.repository.post.PostRepositoryContract
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject
constructor(private val postRemoteDataSource: PostRemoteDataSourceContract,
            private val commentRemoteDataSource: CommentRemoteDataSourceContract,
            private val userRemoteDataSource: UserRemoteDataSourceContract)
    : PostRepositoryContract {

    override fun getUserById(userId: Int): Single<User> {
        return userRemoteDataSource.getUserById(userId)
    }

    override fun getAllPosts(): Single<List<Feed>> {
        return postRemoteDataSource.getAllPosts()
            .toObservable()
            .flatMapIterable { it }
            .concatMap { post ->
                return@concatMap getUserById(post.userId!!)
                    .toObservable()
                    .flatMap { user ->
                        return@flatMap Single.just(
                            Feed(
                                id = post.id!!,
                                title = post.title,
                                body = post.body,
                                username = user.username!!,
                                company_name = user.company.name!!
                            )
                        ).toObservable()
                    }
            }.toList()
            .flatMap {
                return@flatMap Single.just(it)
            }
    }

    override fun getPostById(postId: Int): Single<PostDetail> {
        return postRemoteDataSource.getPostById(postId)
            .flatMap { post ->
                return@flatMap getUserById(post.userId!!)
                    .flatMap { user ->
                        getCommentsByPostId(postId)
                            .flatMap { comment ->
                                Single.just(
                                    PostDetail(
                                        id = post.id!!,
                                        userId = post.userId!!,
                                        title = post.title,
                                        name = user.name!!,
                                        username = user.username!!,
                                        body = post.body,
                                        commentList = comment
                                    )
                                )
                            }
                    }
            }
    }

    override fun getCommentsByPostId(postId: Int): Single<List<Comment>> {
        return commentRemoteDataSource.getCommentsByPostId(postId)
    }

}
