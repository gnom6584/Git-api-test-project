package com.example.gittestproject.searchscreen

import android.util.Log
import android.widget.Toast
import androidx.paging.PositionalDataSource
import com.example.gittestproject.Application
import com.example.gittestproject.github.GithubRepository
import kotlinx.coroutines.*
import java.lang.Exception

class GithubDataSource(val searchName: String, val githubRepository: GithubRepository, val scope: CoroutineScope) : PositionalDataSource<RepositoryViewModel>() {

    private fun loadRepositoriesAtRange(start: Int, end: Int, callback: (List<RepositoryViewModel>, Int) -> Unit) {
            scope.launch {
                try {
                    githubRepository.searchRepositoriesByName(
                        searchName,
                        start,
                        end
                    )?.let { searchResult ->
                        val repositoryViewModels =
                            List(searchResult.items.size) { RepositoryViewModel() }
                        searchResult.items.zip(repositoryViewModels).forEach { pair ->
                            val (model, viewModel) = pair
                            with(viewModel) {
                                withContext(Dispatchers.Main) {
                                    name.value = (model.name)
                                    fullName.value = (model.fullName)
                                    description.value = (model.description)
                                    val newOwner = RepositoryViewModel.OwnerViewModel()
                                    newOwner.avatarUrl.value = (model.owner.avatarUrl)
                                    newOwner.login.value = (model.owner.login)
                                    owner.value = (newOwner)
                                    languages.value = model.language?.let { listOf(it) } ?: listOf()
//                                    try {
//
//                                        withContext(Dispatchers.IO) {
//                                            githubRepository.getLanguagesByUrl(model.languagesUrl).let {
//                                                    Log.d("TAG", it.joinToString())
//                                                    withContext(Dispatchers.Main) {
//                                                        languages.value = (it)
//                                                    }
//                                                }
//                                        }
//                                    }
//                                    catch (ex: Exception) {
//                                        languages.value = listOf(ex.message!!)
//                                    }
                                }
                            }
                        }

                        callback(repositoryViewModels, searchResult.totalCount)
                    }
                }
                catch (ex: Exception) {
                    GlobalScope.launch(Dispatchers.Main){
                        Toast.makeText(
                            Application.component.getContext(),
                            ex.message!!,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<RepositoryViewModel>
    ) = loadRepositoriesAtRange(params.startPosition, params.loadSize) { result, totalCount -> callback.onResult(result) }


    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<RepositoryViewModel>
    ) = loadRepositoriesAtRange(params.requestedStartPosition, params.requestedLoadSize) { result, totalCount -> callback.onResult(result, 0, totalCount) }
}