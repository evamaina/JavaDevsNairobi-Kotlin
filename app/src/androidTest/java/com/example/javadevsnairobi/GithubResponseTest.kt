package com.example.javadevsnairobi

import com.example.javadevsnairobi.models.GithubUser
import com.example.javadevsnairobi.models.GithubUsersResponse

import org.junit.Test

import java.util.ArrayList

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertArrayEquals

class GithubResponseTest {
    private val username = "username"
    private val profilePic = "profilePic"
    private val url = "url"
    private val followers = "followers"
    private val repos = "repos"

    private val githubUsersResponse = GithubUsersResponse()
    private val githubUsers = GithubUser(username, profilePic, url, followers, repos)
    private val githubUsers1 = GithubUser(username, profilePic, url, followers, repos)
    private val githubUsersArrayList = ArrayList<GithubUser>()

    @Test
    fun testGithubResponse() {

        githubUsersArrayList.add(githubUsers)
        githubUsersArrayList.add(githubUsers1)
        githubUsersResponse.setGithubUsers(githubUsersArrayList)
        githubUsersResponse.total_count = "2"

        assertEquals("2", githubUsersResponse.total_count)
        assertArrayEquals(arrayOf<ArrayList<*>>(githubUsersArrayList), arrayOf<List<*>>(githubUsersResponse.githubUsers))
    }
}
