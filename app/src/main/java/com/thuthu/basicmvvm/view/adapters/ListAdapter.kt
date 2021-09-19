package com.thuthu.basicmvvm.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thuthu.basicmvvm.R
import  com.thuthu.basicmvvm.data.model.UserResponse
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter(private val users: MutableList<UserResponse>,
                  private val listener: (UserResponse) -> Unit): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(users[position], position)
    }

    fun setList(userList: List<UserResponse>) {
        this.users.clear()
        this.users.addAll(userList)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(user: UserResponse, position: Int)   {

            view.listNameTextView.text = user.mName
            view.listEmailTextView.text = user.mEmail
            view.listAddressTextView.text = user.address?.city + " , " + user.address?.street
            view.listPhoneTextView.text = user.phone

            view.setOnClickListener { listener(users?.get(position))
            }
        }
    }
}