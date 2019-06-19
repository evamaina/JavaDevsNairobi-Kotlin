package com.example.javadevsnairobi


import android.os.Parcel

import com.example.javadevsnairobi.models.GithubUser

import org.junit.Test

import junit.framework.TestCase.assertEquals
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat

class GithubUserTest {
    private val username = "username"
    private val profilePic = "profilePic"
    private val url = "url"
    private val followers = "followers"
    private val public_repos = "public_repos"
    private val company = "company"
    private val location = "location"
    private val repos_url = "repos_url"

    private fun userInfo(): GithubUser {
        val githubUsers = GithubUser()
        githubUsers.username = username
        githubUsers.profilePic = profilePic
        githubUsers.url = url
        githubUsers.followers = followers
        githubUsers.repositories = public_repos
        githubUsers.company = company
        githubUsers.repos_url = repos_url
        githubUsers.location = location

        return githubUsers

    }

    @Test
    fun testUserDetail() {
        val githubUsers = userInfo()
        assertEquals(username, githubUsers.username)
        assertEquals(profilePic, githubUsers.profilePic)
        assertEquals(url, githubUsers.url)
        assertEquals(followers, githubUsers.followers)
        githubUsers.repositories = public_repos
        assertEquals(public_repos, githubUsers.repositories)
        assertEquals(company, githubUsers.company)
        assertEquals(location, githubUsers.location)
        assertEquals(repos_url, githubUsers.repos_url)
    }

    @Test
    fun GithubUserParcelable() {
        val githubUsers = userInfo()
        val parcel = Parcel.obtain()
        githubUsers.writeToParcel(parcel, githubUsers.describeContents())
        parcel.setDataPosition(0)
        val createdFromParcel = GithubUser.CREATOR.createFromParcel(parcel)
        assertThat<String>(createdFromParcel.username, `is`("username"))
        assertThat<String>(createdFromParcel.profilePic, `is`("profilePic"))
        assertThat<String>(createdFromParcel.url, `is`("url"))
        assertThat<String>(createdFromParcel.followers, `is`("followers"))
        assertThat<String>(createdFromParcel.repositories, `is`("public_repos"))
        assertThat<String>(createdFromParcel.company, `is`("company"))


    }


}
