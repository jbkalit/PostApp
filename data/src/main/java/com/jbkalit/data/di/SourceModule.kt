package com.jbkalit.data.di

import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.service.AlbumService
import com.jbkalit.data.service.CommentService
import com.jbkalit.data.service.PostService
import com.jbkalit.data.service.UserService
import com.jbkalit.data.source.album.remote.AlbumRemoteDataSource
import com.jbkalit.data.source.album.remote.AlbumRemoteDataSourceContract
import com.jbkalit.data.source.comment.remote.CommentRemoteDataSource
import com.jbkalit.data.source.comment.remote.CommentRemoteDataSourceContract
import com.jbkalit.data.source.post.remote.PostRemoteDataSource
import com.jbkalit.data.source.post.remote.PostRemoteDataSourceContract
import com.jbkalit.data.source.user.remote.UserRemoteDataSource
import com.jbkalit.data.source.user.remote.UserRemoteDataSourceContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {

    @Provides
    @Singleton
    fun providesPostRemoteDataSource(postService: PostService,
                                     schedulerProvider: BaseSchedulerProvider)
            : PostRemoteDataSourceContract = PostRemoteDataSource(postService, schedulerProvider)

    @Provides
    @Singleton
    fun providesCommentRemoteDataSource(commentService: CommentService,
                                        schedulerProvider: BaseSchedulerProvider)
            : CommentRemoteDataSourceContract = CommentRemoteDataSource(commentService, schedulerProvider)

    @Provides
    @Singleton
    fun providesUserRemoteDataSource(userService: UserService,
                                     schedulerProvider: BaseSchedulerProvider)
            : UserRemoteDataSourceContract = UserRemoteDataSource(userService, schedulerProvider)

    @Provides
    @Singleton
    fun providesAlbumRemoteDataSource(albumService: AlbumService,
                                      schedulerProvider: BaseSchedulerProvider)
            : AlbumRemoteDataSourceContract = AlbumRemoteDataSource(albumService, schedulerProvider)

}
