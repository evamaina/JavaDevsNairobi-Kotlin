package com.example.javadevsnairobi.presenter

import android.annotation.SuppressLint

import com.example.javadevsnairobi.models.GithubUser
import com.example.javadevsnairobi.models.GithubUsersResponse
import com.example.javadevsnairobi.service.GithubService
import com.example.javadevsnairobi.view.LoadListener
import com.example.javadevsnairobi.view.UserListView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubPresenter(private val userListView: UserListView, internal var loadListener: LoadListener) {
    private var githubService: GithubService? = null

    init {
        if (this.githubService == null) {
            this.githubService = GithubService()
        }
    }

    fun getGithubUsers() {
        githubService!!
                .githubAPI
                ._users
                .enqueue(object : Callback<GithubUsersResponse> {
                    @SuppressLint("LongLogTag")
                    override fun onResponse(call: Call<GithubUsersResponse>, response: Response<GithubUsersResponse>) {
                        userListView.usersListReady(response.body()!!.githubUsers)
                        loadListener.isLoading(true)
                    }

                    override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {
                        try {
                            throw InterruptedException("Something is wrong!")
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                    }
                })
    }


    fun getUserDetails(username: String) {
        githubService!!.githubAPI.getUser(username).enqueue(object : Callback<GithubUser> {
            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                userListView.userDetails(response.body())
                loadListener.isLoading(true)
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {

            }
        })
    }

}
