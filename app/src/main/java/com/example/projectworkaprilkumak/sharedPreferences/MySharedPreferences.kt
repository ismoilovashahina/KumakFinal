package com.example.projectworkaprilkumak.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.projectworkaprilkumak.datas.UrgentFdata
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MySharedPreferences {
    companion object {
        private var mySharedPreferences = MySharedPreferences()
        private var splash: SharedPreferences? = null
        private val gson = Gson()
        private val type: Type = object : TypeToken<List<UrgentFdata>>() {}.type
        private var post:SharedPreferences? = null
        private lateinit var editor: SharedPreferences.Editor

        fun getInstance(context: Context): MySharedPreferences {
//            if (splash == null) {
//                splash = context.getSharedPreferences("context", Context.MODE_PRIVATE)
//            }
            if (post==null){
                post = context.getSharedPreferences("context", Context.MODE_PRIVATE)
            }
            return mySharedPreferences
        }

    }

fun getPost():MutableList<UrgentFdata>{
    var posts = arrayListOf<UrgentFdata>()
    val s = post?.getString("postList","")
    if (s!=""){
        posts = gson.fromJson(s, type)
    }
    return posts
}
    fun setPost(posts:ArrayList<UrgentFdata>):Boolean{
        editor = post!!.edit()
        editor.putString("postList", gson.toJson(posts))
        return editor.commit()
    }
//        fun getStatus(): Boolean {
//            return splash!!.getBoolean("status", false)
//        }
//
//        fun setStatus(status: Boolean) {
//            editor = splash!!.edit()
//            editor.putBoolean("status", status).apply()
//        }
}