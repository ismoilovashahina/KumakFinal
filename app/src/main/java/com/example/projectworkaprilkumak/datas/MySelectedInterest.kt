package com.example.projectworkaprilkumak.datas

// SelectInterest(var interestIcon:Int,
// var interestText: String,var status:Boolean=false) : java.io.Serializable
class MySelectedInterest {
    var id:Int? = null
    var interest:String? = null
    constructor()
    constructor(id:Int?,interest:String?){
        this.id = id
        this.interest = interest
    }
    constructor(interest:String?){
        this.interest = interest
    }
}