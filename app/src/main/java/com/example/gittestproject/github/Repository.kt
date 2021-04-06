package com.example.gittestproject.github

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("language")
    val language: String?,
    @SerializedName("languages_url")
    val languagesUrl: String) {

    data class Owner(
        @SerializedName("login")
        val login: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
    )
}