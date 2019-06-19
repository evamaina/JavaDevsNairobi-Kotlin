package com.example.javadevsnairobi.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class GithubUser : Parcelable {
    @SerializedName("login")
    var username: String? = null

    @SerializedName("avatar_url")
    var profilePic: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("followers")
    var followers: String? = null

    @SerializedName("public_repos")
    var repositories: String? = null

    @SerializedName("repos_url")
    var repos_url: String? = null

    @SerializedName("company")
    var company: String? = null

    @SerializedName("location")
    var location: String? = null


    constructor(username: String, profilePic: String, url: String, followers: String, public_repos: String, company: String, repos_url: String, location: String) {
        this.username = username
        this.profilePic = profilePic
        this.url = url
        this.followers = followers
        this.repositories = public_repos
        this.company = company
        this.repos_url = repos_url
        this.location = location

    }

    constructor() {}

    constructor(username: String, profilePic: String, url: String, followers: String, repos: String) {}

    private constructor(`in`: Parcel) {
        username = `in`.readString()
        profilePic = `in`.readString()
        url = `in`.readString()
        followers = `in`.readString()
        repositories = `in`.readString()
        company = `in`.readString()
        location = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(username)
        dest.writeString(profilePic)
        dest.writeString(url)
        dest.writeString(followers)
        dest.writeString(repositories)
        dest.writeString(company)
    }

    companion object {

        val CREATOR: Parcelable.Creator<GithubUser> = object : Parcelable.Creator<GithubUser> {
            override fun createFromParcel(source: Parcel): GithubUser {
                return GithubUser(source)
            }

            override fun newArray(size: Int): Array<GithubUser> {
                return arrayOfNulls(size)
            }
        }
    }
}

