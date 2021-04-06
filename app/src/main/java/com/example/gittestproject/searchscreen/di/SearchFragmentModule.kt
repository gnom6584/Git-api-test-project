package com.example.gittestproject.searchscreen.di

import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gittestproject.Application
import com.example.gittestproject.MainActivity
import com.example.gittestproject.MainViewModel
import com.example.gittestproject.databinding.FragmentSearchBinding
import com.example.gittestproject.searchscreen.SearchFragment
import com.example.gittestproject.searchscreen.SearchListAdapter
import dagger.Module
import dagger.Provides

@Module
class SearchFragmentModule(private val fragment: SearchFragment, private val binding: FragmentSearchBinding) {

    @Provides
    fun getRecyclerView(): RecyclerView = binding.recyclerView

    @Provides
    fun getAdapter(viewModel: MainViewModel) = SearchListAdapter(fragment)

    @Provides
    fun getViewModel() = ViewModelProvider(fragment.requireActivity(), Application.component.getViewModelFactory())[MainViewModel::class.java]

    @Provides
    fun getEditText() : SearchView = binding.searchView

    @Provides
    fun getRootLayout() : LinearLayout = binding.rootLayout

    @Provides
    fun getScrollOnTopButton() : Button = binding.scrollToTopButton

    @Provides
    fun getLifeCycleOwner() = fragment.context as LifecycleOwner

    @Provides
    fun getNavigationController() = (fragment.activity as MainActivity).navigationController
}