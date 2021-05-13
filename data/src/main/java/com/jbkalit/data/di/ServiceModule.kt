package com.jbkalit.data.di

import com.jbkalit.data.service.AlbumService
import com.jbkalit.data.service.CommentService
import com.jbkalit.data.service.PostService
import com.jbkalit.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun providesPostService(retrofit: Retrofit)
            : PostService = retrofit.create(PostService::class.java)

    @Provides
    @Singleton
    fun providesCommentService(retrofit: Retrofit)
            : CommentService = retrofit.create(CommentService::class.java)

    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit)
            : UserService = retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun providesAlbumService(retrofit: Retrofit)
            : AlbumService = retrofit.create(AlbumService::class.java)

}
