package com.jbkalit.data.di

import com.jbkalit.data.repository.album.AlbumRepository
import com.jbkalit.data.repository.post.PostRepository
import com.jbkalit.data.repository.user.UserRepository
import com.jbkalit.data.source.album.remote.AlbumRemoteDataSourceContract
import com.jbkalit.data.source.comment.remote.CommentRemoteDataSourceContract
import com.jbkalit.data.source.post.remote.PostRemoteDataSourceContract
import com.jbkalit.data.source.user.remote.UserRemoteDataSourceContract
import com.jbkalit.domain.repository.album.AlbumRepositoryContract
import com.jbkalit.domain.repository.post.PostRepositoryContract
import com.jbkalit.domain.repository.user.UserRepositoryContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesPostRepository(postRemoteDataSource: PostRemoteDataSourceContract,
                               commentRemoteDataSource: CommentRemoteDataSourceContract,
                               userRemoteDataSource: UserRemoteDataSourceContract)
            : PostRepositoryContract = PostRepository(postRemoteDataSource, commentRemoteDataSource,
            userRemoteDataSource)

    @Provides
    @Singleton
    fun providesUserRepository(userRemoteDataSource: UserRemoteDataSourceContract)
            : UserRepositoryContract = UserRepository(userRemoteDataSource)

    @Provides
    @Singleton
    fun providesAlbumRepository(albumRemoteDataSource: AlbumRemoteDataSourceContract)
            : AlbumRepositoryContract = AlbumRepository(albumRemoteDataSource)

}
