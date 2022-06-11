package com.example.firebaseapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.database_list_fragment.view.*
import kotlinx.android.synthetic.main.user_list_fragment.*
import kotlinx.android.synthetic.main.user_list_fragment.view.*


class UserListFragment : Fragment() {

    private val args by navArgs<UserListFragmentArgs>()

    private lateinit var logout:Button
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var dao:UsersDAO
    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_list_fragment, container,false)
        view.idUsername.setText(args.username)
        logout=view.findViewById(R.id.logut)

        val recyclerView = view.RecyclerViewUser
        adapter = UsersAdapter()
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        dao = UsersDAO()
        swipe = view.findViewById(R.id.SwipUser)
        loadData()


        logout.setOnClickListener(){
            Firebase.auth.signOut()
            val action = UserListFragmentDirections.actionLogout()
            findNavController().navigate(action)
        }
        view.FABaddtouser.setOnClickListener(){
            val action=UserListFragmentDirections.actionAddToUser(args.username)
            findNavController().navigate(action)
        }

        return view
    }

    private fun loadData() {
        swipe.isRefreshing = true
        dao.get().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val emps: ArrayList<Users> = ArrayList<Users>()
                for (data in snapshot.children) {
                    val emp: Users = data.getValue(Users::class.java)!!
                    if(emp.key==args.username){
                        emps.add(emp)
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