package com.jbkalit.data.di

import com.jbkalit.domain.repository.album.AlbumRepositoryContract
import com.jbkalit.domain.repository.post.PostRepositoryContract
import com.jbkalit.domain.repository.user.UserRepositoryContract
import com.jbkalit.domain.usecase.album.AlbumUseCase
import com.jbkalit.domain.usecase.album.AlbumUseCaseContract
import com.jbkalit.domain.usecase.feed.FeedUseCase
import com.jbkalit.domain.usecase.feed.FeedUseCaseContract
import com.jbkalit.domain.usecase.post.PostUseCase
import com.jbkalit.domain.usecase.post.PostUseCaseContract
import com.jbkalit.domain.usecase.user.UserUseCase
import com.jbkalit.domain.usecase.user.UserUseCaseContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesFeedUseCasePost(postRepository: PostRepositoryContract)
            : FeedUseCaseContract = FeedUseCase(postRepository)

    @Provides
    @Singleton
    fun providesPostUseCasePost(postRepository: PostRepositoryContract)
            : PostUseCaseContract = PostUseCase(postRepository)

    @Provides
    @Singleton
    fun providesUserUseCasePost(userRepository: UserRepositoryContract)
            : UserUseCaseContract = UserUseCase(userRepository)

    @Provides
    @Singleton
    fun providesAlbumUseCasePost(albumRepository: AlbumRepositoryContract)
            : AlbumUseCaseContract = AlbumUseCase(albumRepository)

}
