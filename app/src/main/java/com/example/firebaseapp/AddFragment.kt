package com.example.firebaseapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.add_fragment.*
import kotlinx.android.synthetic.main.database_list_fragment.view.*
import kotlinx.android.synthetic.main.user_list_fragment.*
import kotlinx.android.synthetic.main.user_list_fragment.view.*


class AddFragment : Fragment() {


    private val args by navArgs<AddFragmentArgs>()

    private lateinit var add:Button
    private lateinit var dropdown: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_fragment, container,false)
        add=view.findViewById(R.id.add_btn)
        dropdown = view.findViewById(R.id.autoCompleteTextView)
        val dao = ItemsDAO()
        val emps: ArrayList<Items> = ArrayList<Items>()

        dao.get().addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    val emp: Items = data.getValue(Items::class.java)!!
                    if(emp.key=="all"){
                        emps.add(emp)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        val arrayAdapterTypes = ArrayAdapter(requireContext() , R.layout.dropdown,resources.getStringArray(R.array.typy))
        dropdown.setAdapter(arrayAdapterTypes)


        add.setOnClickListener(){
            var check = true
            loop@ for(it in emps)
            {
                if(it.name.toString()==idEdtName.text.trim().toString())
                {
                    check=false
                    break@loop
                }
            }
            if(idEdtName.text.trim().isNotEmpty()&&idEdtDesc.text.trim().isNotEmpty()&&check) {
                dao.add(
                    Items(
                        idEdtName.text.toString(),
                        idEdtDesc.text.toString(),
                        autoCompleteTextView.text.toString()
                    )
                )
                val action = AddFragmentDirections.actionDone(args.username)
                findNavController().navigate(action)
            }
            else {
                if(check){
                    Toast.makeText(context, "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()

                }
                else{
                Toast.makeText(context, "Taka nazwa juz istnieje", Toast.LENGTH_SHORT).show()
            }
            }
        }

        return view
    }
}