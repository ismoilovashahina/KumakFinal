package com.example.projectworkaprilkumak.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.projectworkaprilkumak.datas.ImageUser
import com.example.projectworkaprilkumak.datas.MyCountry
import com.example.projectworkaprilkumak.datas.MyFundraisingData
import com.example.projectworkaprilkumak.datas.MyProfie
import com.example.projectworkaprilkumak.datas.MySelectedInterest
import com.example.projectworkaprilkumak.datas.MySortData
import com.example.projectworkaprilkumak.datas.MyUser
import com.example.projectworkaprilkumak.datas.SelectInterest


class MyBase(var context: Context):SQLiteOpenHelper(context,"mybas.db",null,1),MyPlan {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "create table image_table(id integer primary key autoincrement not null,image_path text not null,image blob not null)"
        val mainQuery = "create table fund(id integer primary key autoincrement not null,title text not null,imgId integer not null,raised integer not null,to_raise integer not null,don integer not null,days integer not null,category text not null,recipients text not null,donation text not null,medical text not null,story text not null)"
        val userQuery = "create table usr(id integer primary key autoincrement not null,email text not null,password text not null,signedn text not null)"
        val profileQuery = "create table profile(id integer primary key autoincrement not null,user_id text not null,name text not null,email text not null,phone text not null,gender text not null,city text not null,path text not null,image blob not null)"
        val countryQuery = "create table country(id integer primary key autoincrement not null,name text not null,flag integer not null,short text not null)"
        val interestQuery = "create table interest(id integer primary key autoincrement not null,interests text not null)"
        val sortQuery = "create table sort(id integer primary key autoincrement not null,sorting text not null)"
        p0?.execSQL(query)
        p0?.execSQL(mainQuery)
        p0?.execSQL(userQuery)
        p0?.execSQL(profileQuery)
        p0?.execSQL(countryQuery)
        p0?.execSQL(interestQuery)
        p0?.execSQL(sortQuery)
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addUser(myUser: MyUser) {
        val wr = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("email",myUser.email)
        contentValues.put("password",myUser.password)
        contentValues.put("signedn",myUser.isSignedIn)
        wr.insert("usr",null,contentValues)
        wr.close()
    }
    override fun getUser(): List<MyUser> {
        val list = ArrayList<MyUser>()
        val query = "select * from usr"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val myUser = MyUser()
                myUser.id = cursor.getInt(0)
                myUser.email = cursor.getString(1)
                myUser.password = cursor.getString(2)
                myUser.isSignedIn = cursor.getString(3)
                list.add(myUser)

            }while (cursor.moveToNext())
        }
        return list
    }
    override fun insertImage(imageUser: ImageUser) {
        val writer = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("image_path",imageUser.absolutePath)
        contentValues.put("image",imageUser.image)
        writer.insert("image_table",null,contentValues)
        writer.close()
    }
    override fun editUser(myUser: MyUser):Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id",myUser.id)
        contentValues.put("email",myUser.email)
        contentValues.put("password",myUser.password)
        contentValues.put("signedn",myUser.isSignedIn)
        return db.update("usr",contentValues,"id = ?", arrayOf(myUser.id.toString()))
    }
    override fun addProile(myProfie: MyProfie) {
        val writer = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("user_id",myProfie.userId)
        contentValues.put("name",myProfie.fullName)
        contentValues.put("email",myProfie.email)
        contentValues.put("phone",myProfie.phoneN)
        contentValues.put("gender",myProfie.gender)
        contentValues.put("city",myProfie.city)
        contentValues.put("path",myProfie.imagePath)
        contentValues.put("image",myProfie.image)
        writer.insert("profile",null,contentValues)
        writer.close()
    }

    override fun getProfile(): List<MyProfie> {
        val list = ArrayList<MyProfie>()
        val query = "select * from profile"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val myProfie = MyProfie()
                myProfie.id = cursor.getInt(0)
                myProfie.userId = cursor.getInt(1)
                myProfie.fullName = cursor.getString(2)
                myProfie.email = cursor.getString(3)
                myProfie.phoneN = cursor.getString(4)
                myProfie.gender = cursor.getString(5)
                myProfie.city = cursor.getString(6)
                myProfie.imagePath = cursor.getString(7)
                myProfie.image = cursor.getBlob(8)
                list.add(myProfie)
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun editProfile(myProfie: MyProfie): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id",myProfie.id)
        contentValues.put("user_id",myProfie.userId)
        contentValues.put("name",myProfie.fullName)
        contentValues.put("email",myProfie.email)
        contentValues.put("phone",myProfie.phoneN)
        contentValues.put("gender",myProfie.gender)
        contentValues.put("city",myProfie.city)
        contentValues.put("path",myProfie.imagePath)
        contentValues.put("image",myProfie.image)
        return db.update("profile",contentValues,"id = ?", arrayOf(myProfie.id.toString()))
    }

    override fun addCountry(myCountry: MyCountry) {
        val writer = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name",myCountry.name)
        contentValues.put("flag",myCountry.flag)
        contentValues.put("short",myCountry.shortName)
        writer.insert("country",null,contentValues)
        writer.close()
    }

    override fun getCountry(): List<MyCountry> {
        val list = ArrayList<MyCountry>()
        val query = "select * from country"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val myCountry = MyCountry()
                myCountry.id = myCountry.id
                myCountry.name = myCountry.name
                myCountry.flag = myCountry.flag
                myCountry.shortName = myCountry.shortName
                list.add(myCountry)
            }while (cursor.moveToNext())
        }
        return list
    }
    override fun editCountry(myCountry: MyCountry): Int {
        val edit = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id",myCountry.id)
        contentValues.put("name",myCountry.name)
        contentValues.put("flag",myCountry.flag)
        contentValues.put("short",myCountry.shortName)
        return edit.update("country",contentValues,"id = ?", arrayOf(myCountry.id.toString()))
    }

    override fun addInterest(mySelectedInterest: MySelectedInterest) {
        val writer =this.writableDatabase
        val contentValues =ContentValues()
        contentValues.put("interests",mySelectedInterest.interest)
        writer.insert("interest",null,contentValues)
        writer.close()
    }

    override fun getAllInterest(): List<MySelectedInterest> {
        val list = ArrayList<MySelectedInterest>()
        val query = "select * from interest"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val mySelectInterest = MySelectedInterest()
                mySelectInterest.id = cursor.getInt(0)
                mySelectInterest.interest = cursor.getString(1)
                list.add(mySelectInterest)

            }while (cursor.moveToNext())
        }
        return list

    }

    override fun editInterest(mySelectedInterest: MySelectedInterest): Int {
        val edit = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id",mySelectedInterest.id)
        contentValues.put("interests",mySelectedInterest.interest)
        return edit.update("interest",contentValues,"id = ?", arrayOf(mySelectedInterest.id.toString()))
    }

    override fun addSort(mySortData: MySortData) {
        val adder = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("sorting",mySortData.sortBy)
        adder.insert("sort",null,contentValues)
        adder.close()
    }

    override fun getAllSort(): ArrayList<MySortData> {
        val list = ArrayList<MySortData>()
        val query ="select * from sort"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val mySortData = MySortData()
                mySortData.id = cursor.getInt(0)
                mySortData.sortBy = cursor.getString(1)
                list.add(mySortData)
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun editSort(mySortData: MySortData): Int {
        val edit = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id",mySortData.id)
        contentValues.put("sorting",mySortData.sortBy)
        return edit.update("sort",contentValues,"id = ?", arrayOf(mySortData.id.toString()))
    }

    override fun getAllImage(): List<ImageUser> {
        val list = ArrayList<ImageUser>()
        val query = "select * from image_table"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val imageUser = ImageUser()
                imageUser.id = cursor.getInt(0)
                imageUser.absolutePath = cursor.getString(1)
                imageUser.image = cursor.getBlob(2)
                list.add(imageUser)
            }while (cursor.moveToNext())
        }
        return list

    }
    override fun addFundraisingData(myFundraisingData: MyFundraisingData) {
        val writer = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("title",myFundraisingData.title)
        contentValues.put("imgId",myFundraisingData.imgId)
        contentValues.put("raised",myFundraisingData.raised)
        contentValues.put("to_raise",myFundraisingData.toRaise)
        contentValues.put("don",myFundraisingData.donN)
        contentValues.put("days",myFundraisingData.daysLeft)
        contentValues.put("category",myFundraisingData.category)
        contentValues.put("recipients",myFundraisingData.nameOfRecipients)
        contentValues.put("donation",myFundraisingData.donationDocuments)
        contentValues.put("medical",myFundraisingData.medicalDocuments)
        contentValues.put("story",myFundraisingData.story)

        writer.insert("fund",null,contentValues)
        writer.close()
    }
    override fun getAllFundraisingData(): ArrayList<MyFundraisingData> {
        val list = ArrayList<MyFundraisingData>()
        val query = "select * from fund"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val myFundraisingData = MyFundraisingData()
                myFundraisingData.id = cursor.getInt(0)
                myFundraisingData.title = cursor.getString(1)
                myFundraisingData.imgId = cursor.getInt(2)
                myFundraisingData.raised = cursor.getInt(3)
                myFundraisingData.toRaise = cursor.getInt(4)
                myFundraisingData.donN = cursor.getInt(5)
                myFundraisingData.daysLeft =cursor.getInt(6)
                myFundraisingData.category = cursor.getString(7)
                myFundraisingData.nameOfRecipients = cursor.getString(8)
                myFundraisingData.donationDocuments =cursor.getString(9)
                myFundraisingData.medicalDocuments = cursor.getString(10)
                myFundraisingData.story = cursor.getString(11)

                list.add(myFundraisingData)


            }while (cursor.moveToNext())
        }
        return list
    }
    override fun editFundraisingData(myFundraisingData: MyFundraisingData):Int {
        val edit = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id",myFundraisingData.id)
        contentValues.put("imgId",myFundraisingData.imgId)
        contentValues.put("raised",myFundraisingData.raised)
        contentValues.put("to_raise",myFundraisingData.toRaise)
        contentValues.put("don",myFundraisingData.donN)
        contentValues.put("days",myFundraisingData.daysLeft)
        contentValues.put("category",myFundraisingData.category)
        contentValues.put("recipients",myFundraisingData.nameOfRecipients)
        contentValues.put("donation",myFundraisingData.donationDocuments)
        contentValues.put("medical",myFundraisingData.medicalDocuments)
        contentValues.put("story",myFundraisingData.story)

        return edit.update("fund",contentValues,"id = ?",arrayOf(myFundraisingData.id.toString()))
    }

}