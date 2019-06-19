package com.example.javadevsnairobi

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import com.example.javadevsnairobi.models.GithubUser
import com.example.javadevsnairobi.models.GithubUsersResponse
import com.example.javadevsnairobi.presenter.GithubPresenter
import com.example.javadevsnairobi.service.GithubService
import com.example.javadevsnairobi.utils.Constants
import com.example.javadevsnairobi.view.LoadListener
import com.example.javadevsnairobi.view.UserListView
import com.squareup.picasso.Picasso

import java.sql.Struct

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity(), UserListView, LoadListener {
    internal var username: String
    internal var url: String
    internal var profile_pic: String
    internal var presenter: GithubPresenter
    internal var followersTextview: TextView
    internal var organizationTextview: TextView
    internal var repoTextview: TextView
    internal var locationTextview: TextView
    internal var usenameTextview: TextView
    internal var urlTextview: TextView
    internal var shareImageview: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "User detail"
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        presenter = GithubPresenter(this, this)
        followersTextview = findViewById(R.id.follow)
        organizationTextview = findViewById(R.id.org)
        repoTextview = findViewById(R.id.repos)
        locationTextview = findViewById(R.id.location)
        urlTextview = findViewById(R.id.url)
        usenameTextview = findViewById(R.id.username_detail)


        intent
        if (intent.extras != null) {
            username = intent.getStringExtra(Constants.USERNAME)
            presenter.getUserDetails(username)
            url = intent.getStringExtra(Constants.URL)
            profile_pic = intent.getStringExtra(Constants.PROFILEPIC)
            val userImageView = findViewById<ImageView>(R.id.avatar)
            Picasso.get().load(profile_pic).into(userImageView)


        }


        urlTextview.text = url

        urlTextview.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/$username"))
            startActivity(browserIntent)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // app icon in action bar clicked; goto parent activity.
                this.finish()
                return true
            }
            R.id.share -> {

                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "Checkout this awesome developer @$username, $url.")
                startActivity(Intent.createChooser(intent, "Share user profile via..."))
                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun usersListReady(githubUsers: List<GithubUser>) {}

    override fun userDetails(user: GithubUser) {
        if (user != null) {
            followersTextview.text = user.followers!! + " Followers"
            organizationTextview.text = user.company
            repoTextview.text = user.repositories!! + " Repositories"
            locationTextview.text = user.location
        }
        usenameTextview.text = user.username
        //        urlTextview.setText(user.getRepos_url());

    }


    override fun isLoading(hasLoaded: Boolean) {

    }
}
