package com.example.gittestproject.di

import com.example.gittestproject.github.GithubRepository
import com.example.gittestproject.github.GithubRepositoryImpl
import com.example.gittestproject.github.GithubRestApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RestModule {
    @Provides
    fun getBaseUrl() = "https://api.github.com/"

    @Provides
    fun getGsonConverter() = GsonConverterFactory.create()

    @Provides
    fun getRetrofit(baseUrl: String, gsonConverterFactory: GsonConverterFactory) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    fun getService(retrofit: Retrofit) = retrofit.create(GithubRestApi::class.java)

    @Provides
    fun getGithubRepository(service: GithubRestApi) : GithubRepository = GithubRepositoryImpl(service)
}