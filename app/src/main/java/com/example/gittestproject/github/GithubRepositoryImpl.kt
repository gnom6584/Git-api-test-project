package com.example.gittestproject.github

import android.util.Log
import java.lang.Exception
import java.lang.String.format
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(val githubRestApi: GithubRestApi) : GithubRepository {
    companion object {
        const val SEARCH_BY_NAME_QUERY_PATTERN = "%s in:name"
    }

    override suspend fun searchRepositoriesByName(
        searchName: String,
        page: Int,
        perPage: Int
    ) = with(githubRestApi.searchRepositories(format(SEARCH_BY_NAME_QUERY_PATTERN, searchName), page, perPage)) {
        Log.d("TAG", raw().request().url().toString())
        if (isSuccessful) body()!! else throw Exception(errorBody()!!.string())
    }


    override suspend fun getLanguagesByUrl(url: String) = with(githubRestApi.getLanguagesByUrl(url)) {
        Log.d("TAG", raw().request().url().toString())
        if(isSuccessful) body()!!.map { it.key } else throw Exception(errorBody()!!.string())
    }

    override suspend fun getUserByLogin(login: String) =
        with(githubRestApi.getUserByLogin(login)) {
            Log.d("TAG", raw().request().url().toString())
            if (isSuccessful) body()!! else throw Exception(errorBody()!!.string())
        }
}