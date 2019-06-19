package com.example.javadevsnairobi.service

import com.example.javadevsnairobi.models.GithubUser
import com.example.javadevsnairobi.models.GithubUsersResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @get:GET("search/users?q=language:java+location:nairobi")
    val _users: Call<GithubUsersResponse>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<GithubUser>


}


