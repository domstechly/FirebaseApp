package com.example.firebaseapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_row_all.view.*
import kotlinx.android.synthetic.main.custom_row_user.view.*

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {


    private var context: Context? = null
    var list: ArrayList<Users> = ArrayList<Users>()
    private var userList = emptyList<Users>()


    fun setData(user: List<Users>){
        this.userList = user
    }

    fun setItems(emp: Collection<Users>?) {
        list.addAll(emp!!)
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row_user, parent, false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val e: Users? = null
        this.onBindViewHolder(holder, position, e)
    }
    fun onBindViewHolder(holder: MyViewHolder, position: Int, e: Users?) {
        val emp: Users = if (e == null) list[position] else e
        holder.itemView.info_name_user.text=emp.name
        holder.itemView.info_desc_user.text=emp.desc
        holder.itemView.info_type_user.text=emp.type

        holder.itemView.idDeleteIcon.setOnClickListener(){

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}