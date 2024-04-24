package com.example.projectworkaprilkumak.datas

class MyUser {
    var id:Int? = null
    var email:String? = null
    var password:String? = null
    var isSignedIn:String? = null
    constructor()
    constructor(id:Int?,email: String?,password: String?,isSignedIn:String?){
        this.id = id
        this.email = email
        this.password = password
        this.isSignedIn = isSignedIn
    }
    constructor(email:String,password:String?,isSignedIn: String?){
        this.email = email
        this.password = password
        this.isSignedIn = isSignedIn
    }
}