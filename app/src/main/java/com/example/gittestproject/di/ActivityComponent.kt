package com.example.gittestproject.di

import com.example.gittestproject.MainActivity
import com.example.gittestproject.NavigationController
import dagger.Component

@Component(modules = [ActivityModel::class])
interface ActivityComponent {

    fun getNavigationController() : NavigationController

    fun inject(activity: MainActivity)
}