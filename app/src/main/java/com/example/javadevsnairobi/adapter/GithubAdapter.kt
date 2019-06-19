package com.example.javadevsnairobi.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import com.example.javadevsnairobi.DetailActivity
import com.example.javadevsnairobi.MainActivity
import com.example.javadevsnairobi.R
import com.example.javadevsnairobi.models.GithubUser
import com.example.javadevsnairobi.utils.Constants
import com.squareup.picasso.Picasso

class GithubAdapter(internal var users: List<GithubUser>?, internal var context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val user_view = inflater.inflate(R.layout.user_view, viewGroup, false)
        return ViewHolder(user_view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val user = users!![i]

        Picasso.get().load(user.profilePic).into(viewHolder.image)
        viewHolder.name.text = user.username
        //        viewHolder.repositories.setText((user.getRepos_url() +" repositories"));
        viewHolder.listener = View.OnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(Constants.USERNAME, user.username)
            intent.putExtra(Constants.URL, user.url)
            intent.putExtra(Constants.PROFILEPIC, user.profilePic)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return if (users != null) users!!.size else 0
    }

}

internal class ViewHolder : RecyclerView.ViewHolder, View.OnClickListener {
    var image: ImageView
    var name: TextView
    var repositories: TextView
    var listener: View.OnClickListener

    constructor(itemView: View, listener: View.OnClickListener) : super(itemView) {
        this.listener = listener
    }

    constructor(itemView: View) : super(itemView) {
        image = itemView.findViewById(R.id.user_image)
        name = itemView.findViewById(R.id.username)
        repositories = itemView.findViewById(R.id.repositories)
        itemView.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        listener.onClick(v)

    }
}
