package com.example.gittestproject

import android.util.Log
import androidx.navigation.NavController
import javax.inject.Inject

class NavigationControllerImpl @Inject constructor(val navController: NavController) : NavigationController {

    override fun navigateFromSearchToUserFragment() {
        navController.navigate(R.id.action_searchFragment_to_userFragment)
    }

    override fun navigateFromUserToSearchScreen() {
        navController.navigate(R.id.action_userFragment_to_searchFragment2)
    }


}