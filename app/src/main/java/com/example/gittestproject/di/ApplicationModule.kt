package com.example.gittestproject.di

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.gittestproject.searchscreen.SearchFragment
import dagger.Module
import dagger.Provides
import java.security.Provider
import java.util.concurrent.Executor

@Module
class ApplicationModule(val applicationContext: Context) {

    @Provides
    fun getContext() = applicationContext

    @Provides
    fun getListConfig() = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPageSize(SearchFragment.PAGE_SIZE)
        .setInitialLoadSizeHint(SearchFragment.PAGE_SIZE)
        .build()

    @Provides
    fun getExecutor() : Executor = ContextCompat.getMainExecutor(applicationContext)
}

