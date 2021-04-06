package com.example.gittestproject.di

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gittestproject.MainActivity
import com.example.gittestproject.NavigationController
import com.example.gittestproject.NavigationControllerImpl
import com.example.gittestproject.R
import dagger.Module
import dagger.Provides

@Module
class ActivityModel(val activity: MainActivity) {

    @Provides
    fun getNavController() = (activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

    @Provides
    fun getNavigationController(navController: NavController, navigationControllerImpl: NavigationControllerImpl) : NavigationController = navigationControllerImpl
}