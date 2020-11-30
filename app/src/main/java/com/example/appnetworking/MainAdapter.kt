package com.example.appnetworking

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_item.view.*

 class MainAdapter(private val usersList: ArrayList<User>): RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.apply {
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
            DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false))


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        Log.d("vartika", "userList[pos]" + usersList[position].name + " pos: " + position)
       holder.bind(usersList[position])
    }

    override fun getItemCount(): Int = usersList.size

     fun addUsers(users: List<User>) {
         this.usersList.apply {
             clear()
             addAll(users)
         }

     }
}