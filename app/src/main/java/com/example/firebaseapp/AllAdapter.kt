package com.example.firebaseapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_row_all.view.*

class AllAdapter(name:String): RecyclerView.Adapter<AllAdapter.MyViewHolder>() {


    private var context: Context? = null
    val username=name
    var list: ArrayList<Items> = ArrayList<Items>()
    private var userList = emptyList<Items>()



    fun setItems(emp: Collection<Items>?) {
        list.clear()
        list.addAll(emp!!)
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row_all, parent, false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val e: Items? = null
        this.onBindViewHolder(holder, position, e)
    }
    fun onBindViewHolder(holder: MyViewHolder, position: Int, e: Items?) {
        val emp: Items = if (e == null) list[position] else e
        val dao=UsersDAO()
        holder.itemView.info_name_all.text=emp.name
        holder.itemView.info_desc_all.text=emp.desc
        holder.itemView.info_type_all.text=emp.type
        holder.itemView.idEditIcon.setOnClickListener(){
            dao.add(Users(username,holder.itemView.info_name_all.text.toString(),holder.itemView.info_desc_all.text.toString(),holder.itemView.info_type_all.text.toString()))
        }
    //    vh.txt_option.setOnClickListener { v ->
    //        val popupMenu = PopupMenu(context, vh.txt_option)
    //        popupMenu.inflate(R.menu.option_menu)
    //        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
    //            when (item.itemId) {
    //                R.id.menu_edit -> {
    //                    val intent = Intent(context, MainActivity::class.java)
    //                    intent.putExtra("EDIT", emp)
    //                    context!!.startActivity(intent)
    //                }
    //                R.id.menu_remove -> {
    //                    val dao = DAOEmployee()
    //                    dao.remove(emp.getKey()).addOnSuccessListener { suc ->
    //                        Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show()
    //                        notifyItemRemoved(position)
    //                        list.remove(emp)
    //                    }.addOnFailureListener { er ->
    //                        Toast.makeText(
    //                            context,
    //                            "" + er.getMessage(),
    //                            Toast.LENGTH_SHORT
    //                        ).show()
    //                    }
    //                }
    //            }
    //            false
    //        }
    //      popupMenu.show()
    //    }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}