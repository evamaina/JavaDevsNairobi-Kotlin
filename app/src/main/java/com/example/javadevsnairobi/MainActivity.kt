package com.example.javadevsnairobi

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
//import android.support.v7.widget.LinearLayoutManager;
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import android.support.test.espresso.idling.CountingIdlingResource
import com.example.javadevsnairobi.models.GithubUser
import com.example.javadevsnairobi.adapter.GithubAdapter
import com.example.javadevsnairobi.models.User
import com.example.javadevsnairobi.presenter.GithubPresenter
import com.example.javadevsnairobi.utils.Constants
import com.example.javadevsnairobi.utils.NetworkUtility
import com.example.javadevsnairobi.view.LoadListener
import com.example.javadevsnairobi.view.UserListView

import java.util.ArrayList


class MainActivity : Activity(), UserListView, SwipeRefreshLayout.OnRefreshListener, LoadListener {
    internal var recyclerView: RecyclerView
    internal var adapter: GithubAdapter
    internal var users: List<GithubUser>? = null
    internal var gridLayoutManager: GridLayoutManager
    internal var githubPresenter: GithubPresenter? = null
    internal var githubUsersList: Parcelable? = null
    internal var swipeRefreshLayout: SwipeRefreshLayout
    internal var dialog: ProgressDialog
    var idlingResourceInTest = CountingIdlingResource("name")
        internal set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gridLayoutManager = GridLayoutManager(this, 2)
        swipeRefreshLayout = findViewById(R.id.swipeRefresh)


        dialog = ProgressDialog(this)
        dialog.setMessage("loading...")
        dialog.show()

        recyclerView = findViewById(R.id.my_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = gridLayoutManager

        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener(this)




        if (savedInstanceState != null) {
            users = savedInstanceState.getParcelableArrayList(USER_LIST_KEY)
            usersListReady(users!!)
        } else {
            idlingResourceInTest.increment()
            fetchUsers()
        }

    }


    override fun usersListReady(githubUsers: List<GithubUser>) {
        adapter = GithubAdapter(githubUsers, this)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    override fun userDetails(user: GithubUser) {


    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        githubUsersList = gridLayoutManager.onSaveInstanceState()
        outState.putParcelable(USER_LIST_KEY, githubUsersList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null)
            githubUsersList = savedInstanceState.getParcelable(USER_LIST_KEY)
    }

    override fun onResume() {
        super.onResume()
        if (githubUsersList != null) {
            gridLayoutManager.onRestoreInstanceState(githubUsersList)
        }
    }

    override fun onRefresh() {
        fetchUsers()
    }

    private fun fetchUsers() {
        if (!NetworkUtility.isConnected(this)) {
            dialog.dismiss()
            Snackbar.make(
                    findViewById(R.id.snackbar_action),
                    "Failed to load !",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("TAP TO RETRY") { fetchUsers() }.setActionTextColor(Color.WHITE)
                    .show()
        }
        val githubPresenter = GithubPresenter(this, this)
        githubPresenter.getGithubUsers()
    }


    override fun isLoading(hasLoaded: Boolean) {
        if (hasLoaded && swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        } else if (hasLoaded && dialog.isShowing) {
            idlingResourceInTest.decrement()
            dialog.dismiss()
        }
    }

    companion object {
        val USER_LIST_KEY = "users_list"
    }
}
