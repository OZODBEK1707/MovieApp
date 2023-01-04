package com.example.movieapp.DataBase

import android.content.Context
import android.content.SharedPreferences
import com.example.movieapp.models.MyMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreference {
    private const val NAME = "catch_file_name0"
    private const val MODE = Context.MODE_PRIVATE

    private lateinit var preferences: SharedPreferences
    fun init(context: Context){
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation:(SharedPreferences.Editor)-> Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var movieLIst: ArrayList<MyMovie>
    get() = gsonStringToArray(preferences.getString("obekt", "[]")!!)
    set(value) = preferences.edit{
        it.putString("obekt", arrayToGsonString(value))
    }
    fun arrayToGsonString(arrayList: ArrayList<MyMovie>): String{
        return Gson().toJson(arrayList)
    }
    fun gsonStringToArray(gsonString: String): ArrayList<MyMovie>{
        val typeToken = object : TypeToken<ArrayList<MyMovie>>() {}.type
        return Gson().fromJson(gsonString, typeToken)
    }

}