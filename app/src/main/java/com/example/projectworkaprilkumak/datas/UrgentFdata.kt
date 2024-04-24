package com.example.projectworkaprilkumak.datas

data class UrgentFdata(
    val viewType: Int,
    var u_i:Int,
    var u_t:String,
    var u_raised:Int,
    var u_toRaise:Int,
    var u_don:Int,
    var u_dLeft:Int,
    var category:MainCategory,
    var bookmarked:Boolean = false) : java.io.Serializable
