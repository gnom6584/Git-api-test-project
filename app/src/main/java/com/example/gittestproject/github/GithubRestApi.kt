package com.example.gittestproject.github

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import javax.inject.Qualifier

interface GithubRestApi {

    @GET("/search/repositories")
    suspend fun searchRepositories(@Query("q") query: String,
                           @Query("page") page: Int,
                           @Query("per_page") perPage: Int)
            : Response<SearchResult<Repository>>

    @GET
    suspend fun getLanguagesByUrl(@Url url:String) : Response<Map<String, Int>>

    @GET("/users/{login}")
    suspend fun getUserByLogin(@Path("login") userLogin: String) : Response<User>
}