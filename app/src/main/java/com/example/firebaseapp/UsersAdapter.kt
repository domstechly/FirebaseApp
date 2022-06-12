package com.example.firebaseapp

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.data.model.User
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.custom_row_all.view.*
import kotlinx.android.synthetic.main.custom_row_user.view.*


class UsersAdapter: RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {


    private var context: Context? = null
    var list: ArrayList<Users> = ArrayList<Users>()
    var listkey: ArrayList<String?> = ArrayList<String?>()
    private var userList = emptyList<Users>()


    fun setData(user: List<Users>){
        this.userList = user
    }

    fun setItems(emp: Collection<Users>?) {
        list.clear()
        list.addAll(emp!!)
    }
    fun setKeys(emp: Collection<String?>?) {
        listkey.clear()
        listkey.addAll(emp!!)
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
        val acckey: String? = if (e == null) listkey[position] else null
        holder.itemView.info_name_user.text=emp.name
        holder.itemView.info_desc_user.text=emp.desc
        holder.itemView.info_type_user.text=emp.type
        holder.itemView.owner.text=emp.key

        holder.itemView.idDeleteIcon.setOnClickListener(){
            val dao = UsersDAO()
            dao.remove(acckey.toString())
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}