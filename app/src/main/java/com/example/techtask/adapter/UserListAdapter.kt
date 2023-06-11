package com.example.techtask.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.techtask.R
import com.example.techtask.model.Data
import com.example.techtask.view.MapsActivity


class UserListAdapter(private val context: Context) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    private val userLists: MutableList<Data> = mutableListOf()

    class UserListViewHolder (itemView: View) : ViewHolder(itemView){
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
        private val profileImage: ImageView = itemView.findViewById(R.id.profileImage)
        val map_loc: ImageButton = itemView.findViewById(R.id.map_loc)

        fun bind(userlist: Data, context: Context) {
            nameTextView.text = userlist.first_name
            emailTextView.text = userlist.email
            Glide.with(context)
                .load(userlist.avatar)
                .circleCrop()
                .into(profileImage)
        }


    }
    fun setData(data: List<Data>) {
        userLists.clear()
        userLists.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListAdapter.UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_userlist, parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListAdapter.UserListViewHolder, position: Int) {
        val userlist = userLists[position]
        holder.bind(userlist, context)
        holder.map_loc.setOnClickListener {

            startActivity(context, Intent(context, MapsActivity::class.java),null)
        }
    }

    override fun getItemCount(): Int {
        return userLists.size
    }
}