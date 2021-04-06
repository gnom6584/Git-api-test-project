package com.example.gittestproject.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.gittestproject.github.GithubRepository
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RestModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun getContext() : Context

    fun getViewModelFactory() : ViewModelFactory
}