package com.example.firebaseapp

import com.google.firebase.database.Exclude
import java.io.Serializable

class Users : Serializable {
    @Exclude
    var key: String? = null
    var name: String? = null
    var desc: String? = null
    var type: String? = null

    constructor() {}
    constructor(key:String?,name: String?, desc: String?, type: String?) {
        this.key = key
        this.name = name
        this.desc = desc
        this.type = type
    }

}
