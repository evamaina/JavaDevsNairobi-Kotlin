package com.example.javadevsnairobi.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubService {


    val githubAPI: GithubAPI
        get() {
            val gson = GsonBuilder().setLenient().create()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
            }
            return retrofit!!.create(GithubAPI::class.java!!)
        }

    companion object {
        private var retrofit: Retrofit? = null
        private val BASE_URL = "https://api.github.com/"
    }
}
