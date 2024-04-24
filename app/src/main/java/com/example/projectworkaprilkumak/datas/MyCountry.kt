package com.example.projectworkaprilkumak.datas

// class Country(var name:String, var flag:Int, var shortName:String) : java.io.Serializable
class MyCountry {
    var id:Int? = null
    var name:String? = null
    var flag:Int? = null
    var shortName:String? = null
    constructor()

    constructor(id:Int?,name:String?,flag:Int?,shortName: String?){
        this.id = id
        this.name = name
        this.flag = flag
        this.shortName = shortName
    }
    constructor(name:String?,flag:Int?,shortName:String?){
        this.name = name
        this.flag = flag
        this.shortName = shortName
    }
}