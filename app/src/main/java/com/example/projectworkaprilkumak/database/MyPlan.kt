package com.example.projectworkaprilkumak.database

import com.example.projectworkaprilkumak.datas.ImageUser
import com.example.projectworkaprilkumak.datas.MyCountry
import com.example.projectworkaprilkumak.datas.MyFundraisingData
import com.example.projectworkaprilkumak.datas.MyProfie
import com.example.projectworkaprilkumak.datas.MySelectedInterest
import com.example.projectworkaprilkumak.datas.MySortData
import com.example.projectworkaprilkumak.datas.MyUser
import com.example.projectworkaprilkumak.datas.SelectInterest

interface MyPlan {
    fun insertImage(imageUser: ImageUser)
    fun getAllImage():List<ImageUser>

    fun addFundraisingData(myFundraisingData: MyFundraisingData)
    fun getAllFundraisingData():List<MyFundraisingData>

    fun editFundraisingData(myFundraisingData: MyFundraisingData):Int

    fun addUser(myUser: MyUser)
    fun getUser():List<MyUser>
    fun editUser(myUser: MyUser):Int

    fun addProile(myProfie: MyProfie)
    fun getProfile():List<MyProfie>
    fun editProfile(myProfie: MyProfie):Int

    fun addCountry(myCountry: MyCountry)
    fun getCountry():List<MyCountry>
    fun editCountry(myCountry: MyCountry):Int

    fun addInterest(mySelectedInterest: MySelectedInterest)
    fun getAllInterest():List<MySelectedInterest>
    fun editInterest(mySelectedInterest: MySelectedInterest):Int
    fun addSort(mySortData: MySortData)
    fun getAllSort():ArrayList<MySortData>
    fun editSort(mySortData: MySortData):Int
}