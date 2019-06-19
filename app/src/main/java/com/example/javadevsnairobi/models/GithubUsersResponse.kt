package com.example.javadevsnairobi.models

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class GithubUsersResponse {

    @SerializedName("items")
    var githubUsers: List<GithubUser>
        internal set

    @SerializedName("total_count")
    var total_count: String? = null

    constructor() {}

    constructor(users: ArrayList<GithubUser>, total_count: String) {
        this.githubUsers = users
        this.total_count = total_count
    }

    fun setGithubUsers(users: ArrayList<GithubUser>) {
        this.githubUsers = users
    }


}
