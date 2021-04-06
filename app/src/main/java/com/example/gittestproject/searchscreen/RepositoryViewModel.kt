package com.example.gittestproject.searchscreen

import androidx.lifecycle.MutableLiveData

class RepositoryViewModel {

    class OwnerViewModel {
        val login = MutableLiveData<String>()

        val avatarUrl = MutableLiveData<String>()
    }

    val name = MutableLiveData<String>()

    val fullName = MutableLiveData<String>()

    val description = MutableLiveData<String>()

    val owner = MutableLiveData<OwnerViewModel>()

    val languages = MutableLiveData<List<String>>()
}