package com.example.gittestproject.userScreen.di

import com.example.gittestproject.MainViewModel
import com.example.gittestproject.NavigationController
import com.example.gittestproject.userScreen.UserFragment
import dagger.Component

@Component(modules = [UserFragmentModule::class])
interface UserFragmentComponent {

    fun getViewModel() : MainViewModel

    fun getNavigationController() : NavigationController

    fun inject(userFragment: UserFragment)
}