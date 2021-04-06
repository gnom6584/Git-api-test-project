package com.example.gittestproject.searchscreen.di

import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gittestproject.MainViewModel
import com.example.gittestproject.NavigationController
import com.example.gittestproject.searchscreen.SearchFragment
import com.example.gittestproject.searchscreen.SearchListAdapter
import dagger.Component
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Singleton
@Component(modules = [SearchFragmentModule::class])
interface SearchFragmentComponent {
    fun getAdapter() : SearchListAdapter

    fun getViewModel() : MainViewModel

    fun getSearchView() : SearchView

    fun getRootLayout() : LinearLayout

    fun getRecyclerView() : RecyclerView

    fun getScrollOnTopButton() : Button

    fun getLifeCycleOwner() : LifecycleOwner

    fun getNavigationController() : NavigationController

    fun inject(fragment: SearchFragment)
}