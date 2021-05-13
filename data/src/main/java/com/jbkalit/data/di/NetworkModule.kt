package com.jbkalit.data.di

import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                })
            .build()
    }

    @Provides
    fun providesBaseUrl(): String {
        return "https://jsonplaceholder.typicode.com/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideScheduler(): BaseSchedulerProvider = SchedulerProvider.getInstance()


}
