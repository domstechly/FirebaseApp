package com.example.firebaseapp

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

class UsersDAO {
    private val databaseReference: DatabaseReference
    fun add(itm: Users?): Task<Void> {
        return databaseReference.push().setValue(itm)
    }

    fun update(key: String?, hashMap: HashMap<String?, Any?>?): Task<Void> {
        return databaseReference.child(key!!).updateChildren(hashMap!!)
    }

    fun remove(key: String?): Task<Void> {
        return databaseReference.child(key!!).removeValue()
    }

    operator fun get(key: String?): Query {
            return databaseReference.ref.child(key!!)
    }

    fun get(): Query {
        return databaseReference
    }

    init {
        val db = FirebaseDatabase.getInstance("https://fir-app-34802-default-rtdb.europe-west1.firebasedatabase.app/")
        databaseReference = db.getReferenceFromUrl("https://fir-app-34802-default-rtdb.europe-west1.firebasedatabase.app/")
    }
}