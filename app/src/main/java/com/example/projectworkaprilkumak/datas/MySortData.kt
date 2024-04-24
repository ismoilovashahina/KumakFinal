package com.example.projectworkaprilkumak.datas

class MySortData {
    var id:Int? = null
    var sortBy:String?  =null
    constructor()
    constructor(id: Int?, sortBy: String?) {
        this.id = id
        this.sortBy = sortBy
    }

    constructor(sortBy: String?) {
        this.sortBy = sortBy
    }


}