package com.example.firebaseapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.database_list_fragment.view.*


class AllListFragment : Fragment() {

    private val args by navArgs<AllListFragmentArgs>()

    private lateinit var back:Button
    private lateinit var swipe:SwipeRefreshLayout
    private lateinit var dao:ItemsDAO
    private lateinit var adapter: AllAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.database_list_fragment, container,false)
        view.idUsernameAll.setText(args.username)
        back=view.findViewById(R.id.back_to_userlist)
        val recyclerView = view.RecyclerViewAll
        adapter = AllAdapter(args.username)
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        dao = ItemsDAO()
        swipe = view.findViewById(R.id.SwipAll)
        loadData()


        back.setOnClickListener(){
            Firebase.auth.signOut()
            val action = AllListFragmentDirections.actionBack(args.username)
            findNavController().navigate(action)
        }
        view.FABaddtoall.setOnClickListener(){
            val action=AllListFragmentDirections.actionAdd(args.username)
            findNavController().navigate(action)
        }

        return view
    }

    private fun loadData() {
        swipe.isRefreshing = true
        dao.get().addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val emps: ArrayList<Items> = ArrayList<Items>()
                val emps2: ArrayList<Items> = ArrayList<Items>()
                for (data in snapshot.children) {
                    val emp: Items = data.getValue(Items::class.java)!!
                    if(emp.key=="all"){
                    emps.add(emp)
                        emps2.add((emp))
                    }
                    else if(emp.key.toString()==args.username){
                        for(it in emps2)
                        {
                            if(it.name==emp.name)
                            {
                                emps.remove(it)
                            }
                        }
                    }
                }
                adapter.setItems(emps)
                adapter.notifyDataSetChanged()
                swipe.setRefreshing(false)
            }

            override fun onCancelled(error: DatabaseError) {
                swipe.setRefreshing(false)
            }
        })
    }
}