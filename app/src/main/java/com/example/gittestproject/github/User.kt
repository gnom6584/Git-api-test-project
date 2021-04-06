package com.example.gittestproject.github

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("followers")
    val followersCount: Int,
    @SerializedName("twitter_username")
    val twitterUserName: String?,
    @SerializedName("blog")
    val blog: String?,
    @SerializedName("email")
    val email: String?

)