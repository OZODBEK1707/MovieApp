package com.example.movieapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.DataBase.MySharedPreference
import com.example.movieapp.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", 0)

        MySharedPreference.init(this)
        val array = MySharedPreference.movieLIst

        val name = array[position].name
        val authors = array[position].author
        val about = array[position].about
        val data = array[position].date

        binding.txtName.text = "{${binding.txtName.text} $name"
        binding.txtAuthors.text = "{${binding.txtAuthors.text} $authors"
        binding.txtAbout.text = "{${binding.txtAbout.text} $about"
        binding.txtData.text = "{${binding.txtData.text} $data"

       binding.btnClose.setOnClickListener{
           finish()
        }
    }

}