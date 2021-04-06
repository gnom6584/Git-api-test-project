package com.example.gittestproject.userScreen

import androidx.lifecycle.MutableLiveData

class UserViewModel {

    val login = MutableLiveData<String>()

    val name = MutableLiveData<String>()

    val blog = MutableLiveData<String>()

    val bio = MutableLiveData<String>()

    val email = MutableLiveData<String>()

    val twitterUserName = MutableLiveData<String>()

    val avatarUrl = MutableLiveData<String>()

    val followersCount = MutableLiveData<Int>()
}