package com.tentwenty.assignment.di

import android.app.Application
import androidx.room.Room
import com.tentwenty.assignment.api.TmdbApi
import com.tentwenty.assignment.data.TenTwentyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(TmdbApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): TmdbApi =
        retrofit.create(TmdbApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : TenTwentyDatabase =
        Room.databaseBuilder(app, TenTwentyDatabase::class.java, "tentwenty_database")
            .build()
}