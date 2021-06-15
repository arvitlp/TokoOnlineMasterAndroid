package com.arvita.tokolistrik.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.arvita.tokolistrik.model.User
import com.google.gson.Gson

class SharedPref(activity: Activity) {
    val login = "login"
    val nama = "name"
    val phone = "phone"
    val email = "email"
    val user = "user"

    val mypref="MAIN_PRF"
    val sp:SharedPreferences

    init {
        sp= activity.getSharedPreferences(mypref,Context.MODE_PRIVATE)
    }
     fun setStatusLogin(status:Boolean){
         sp.edit().putBoolean(login,status).apply()
     }

    fun getStatusLogin():Boolean{
        return sp.getBoolean(login,false)
    }
    fun setString(key:String,value:String){
        sp.edit().putString(key,value).apply()
    }

    //user
    fun setUser(value: User){
        val data = Gson().toJson(value,User::class.java)
        sp.edit().putString(user,data).apply()
    }

    fun getUser(): User?{
        val data = sp.getString(user,null)?: return null
        return Gson().fromJson<User>(data, User::class.java)
    }

    fun getString(key:String): String{
        return sp.getString(key,"")!!
    }



}
