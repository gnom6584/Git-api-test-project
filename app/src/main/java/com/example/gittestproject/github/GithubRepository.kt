package com.example.gittestproject.github

import java.lang.Exception

interface GithubRepository {

    suspend fun searchRepositoriesByName(searchName : String, page: Int, perPage: Int) : SearchResult<Repository>?

    suspend fun getLanguagesByUrl(url: String) : List<String>

    suspend fun getUserByLogin(login: String) : User?
}