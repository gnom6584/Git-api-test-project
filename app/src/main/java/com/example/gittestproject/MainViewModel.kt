package com.example.gittestproject

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.gittestproject.github.GithubRepository
import com.example.gittestproject.github.User
import com.example.gittestproject.searchscreen.GithubDataSource
import com.example.gittestproject.searchscreen.RepositoryViewModel
import com.example.gittestproject.userScreen.UserViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.concurrent.Executor
import javax.inject.Inject
import kotlin.math.log

class MainViewModel
@Inject constructor(private val githubRepository: GithubRepository, private val pageListConfig: PagedList.Config, private val executor: Executor)
    : ViewModel(), CoroutineScope {


    var currentSearchName= ""
        private set

    val selectedUser: LiveData<UserViewModel>
        get() = mSelectedUser

    var selectedUserLogin = ""
        private set

    val pagedList: LiveData<PagedList<RepositoryViewModel>>
        get() = mDataSource

    var listPosition = 0

    private var mDataSource = MutableLiveData<PagedList<RepositoryViewModel>>()

    private var mSelectedUser = MutableLiveData<UserViewModel>()

    private var job = Job()

    override val coroutineContext = Dispatchers.IO + job

    private val scope = CoroutineScope(coroutineContext)

    fun refreshSearch() {
        val pagedList = PagedList.Builder<Int, RepositoryViewModel>(
            GithubDataSource(
                currentSearchName,
                githubRepository,
                scope
            ), pageListConfig)
            .setFetchExecutor(executor)
            .setNotifyExecutor(executor)
            .build()
        mDataSource.value = pagedList
    }

    fun refreshUser() {
        selectUser(selectedUserLogin)
    }

    fun doSearch(repoName: String) {
        if(currentSearchName != repoName) {
            currentSearchName = repoName
            refreshSearch()
        }
    }

    fun selectUser(login: String) {
        selectedUserLogin = login
        mSelectedUser.value = null

       // val mockUser = User("User",
     //       "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficons8.ru%2Ficon%2FWYXdlkP8ct6I%2Fblue-square&psig=AOvVaw2AsPVvNoGuGMdKEFKPww0f&ust=1617815834015000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPCjv-SP6u8CFQAAAAAdAAAAABAD",
      //  "User userovich", "Крутой чел", 1031241, "", "", "user@gmail.com")

        scope.launch(Dispatchers.IO) {
            try {
                val model = githubRepository.getUserByLogin(login) ?: return@launch

                fun validate(value: String?) = value != null && value != "null" && value.isNotEmpty()

                withContext(Dispatchers.Main) {
                    with(UserViewModel()) {
                        name.value = model.name
                        avatarUrl.value = model.avatarUrl
                        if(validate(model.blog))
                            blog.value = model.blog
                        if(validate(model.email))
                            email.value = model.email
                        if(validate(model.bio))
                            bio.value = model.bio
                        if(validate(model.twitterUserName))
                            twitterUserName.value = model.twitterUserName
                        followersCount.value = model.followersCount
                        this.login.value = model.login
                        mSelectedUser.value = this
                    }
                }
                Log.d("TAG", model.toString())

            }
            catch (ex: Exception) {
                Log.d("TAG", ex.message.toString())
                scope.launch(Dispatchers.Main) {
                    Toast.makeText(Application.context, ex.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}