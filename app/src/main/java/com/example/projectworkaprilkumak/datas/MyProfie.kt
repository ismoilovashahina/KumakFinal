package com.example.projectworkaprilkumak.datas

// var fullN:String, var email:String, var phoneN:String, var gender:String, var city:String)
class MyProfie {
    var id:Int? = null
    var userId:Int? = null
    var fullName:String? = null
    var email:String? = null
    var phoneN:String? = null
    var gender:String? = null
    var city:String? = null
    var imagePath:String? = null
    var image:ByteArray? = null
    constructor()
    constructor(id:Int?,userId:Int?,fullName:String?,email:String?,phoneN:String?,gender:String?,city:String,imagePath:String?,image:ByteArray?){
        this.id = id
        this.userId = userId
        this.fullName = fullName
        this.email = email
        this.phoneN = phoneN
        this.gender = gender
        this.city = city
        this.imagePath = imagePath
        this.image = image
    }
    constructor(userId: Int?,fullName:String?,email:String?,phoneN:String?,gender:String?,city:String,imagePath: String?,image: ByteArray?){
        this.userId = userId
        this.fullName = fullName
        this.email = email
        this.phoneN = phoneN
        this.gender = gender
        this.city = city
        this.imagePath = imagePath
        this.image = image
    }
}