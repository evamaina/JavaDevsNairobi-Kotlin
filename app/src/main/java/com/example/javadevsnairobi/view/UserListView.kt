package com.example.javadevsnairobi.view

import com.example.javadevsnairobi.models.GithubUser

interface UserListView {

    fun usersListReady(githubUsers: List<GithubUser>)

    fun userDetails(user: GithubUser)

}
