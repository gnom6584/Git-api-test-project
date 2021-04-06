package com.example.gittestproject.userScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.gittestproject.*
import com.example.gittestproject.databinding.FragmentSearchBinding
import com.example.gittestproject.databinding.FragmentUserBinding
import com.example.gittestproject.userScreen.di.DaggerUserFragmentComponent
import com.example.gittestproject.userScreen.di.UserFragmentModule
import javax.inject.Inject

class UserFragment : Fragment(), OnBackPressedListener {

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var navigationController: NavigationController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        val binding = DataBindingUtil.inflate<FragmentUserBinding>(inflater, R.layout.fragment_user, container, false)

        DaggerUserFragmentComponent.builder().userFragmentModule(UserFragmentModule(this)).build().inject(this)
        binding.mainViewModel = viewModel
        binding.fragment = this@UserFragment

        viewModel.selectedUser.observe((this as LifecycleOwner), Observer {
            binding.viewModel = it
        })

        (activity as MainActivity).onBackPressedListener = this

        return binding.root
    }

    override fun onDestroyView() {
        (activity as MainActivity).onBackPressedListener = null

        super.onDestroyView()
    }

    companion object {
        fun newInstance() = UserFragment()
    }

    override fun onBackPressed() {
        navigationController.navigateFromUserToSearchScreen()
    }
}