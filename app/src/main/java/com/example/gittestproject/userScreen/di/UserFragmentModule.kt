package com.example.gittestproject.userScreen.di

import androidx.lifecycle.ViewModelProvider
import com.example.gittestproject.Application
import com.example.gittestproject.MainActivity
import com.example.gittestproject.MainViewModel
import com.example.gittestproject.userScreen.UserFragment
import dagger.Module
import dagger.Provides

@Module
class UserFragmentModule(val fragment: UserFragment) {

    @Provides
    fun getViewModel() = ViewModelProvider(fragment.requireActivity(), Application.component.getViewModelFactory())[MainViewModel::class.java]

    @Provides
    fun getNavigationController() = (fragment.activity as MainActivity).navigationController
}