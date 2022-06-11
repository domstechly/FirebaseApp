package com.example.firebaseapp

import com.google.firebase.database.Exclude
import java.io.Serializable

class Items : Serializable {
    @Exclude
    var key: String? = null
    var name: String? = null
    var desc: String? = null
    var type: String? = null

    constructor() {}
    constructor(name: String?, desc: String?, type: String?) {
        this.key = "all"
        this.name = name
        this.desc = desc
        this.type = type
    }

}
