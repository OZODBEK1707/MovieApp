package com.example.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapp.DataBase.MySharedPreference
import com.example.movieapp.databinding.ActivityAddBinding
import com.example.movieapp.models.MyMovie

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MySharedPreference.init(this)

        var list = MySharedPreference.movieLIst
        val name = intent.getStringExtra("name")
        val author = intent.getStringExtra("author")
        val about = intent.getStringExtra("about")
        val date = intent.getStringExtra("data")
        val position = intent.getStringExtra("position")
        binding.apply {
            edtName.setText(name)
            edtAuthor.setText(author)
            edtAbout.setText(about)
            edtDate.setText(date)
        }
        binding.apply {
            btnSave.setOnClickListener {
                val myMovie = MyMovie(edtName.text.toString().trim(),
                    edtAuthor.text.toString().trim(),
                    edtAbout.text.toString().trim(),
                    edtDate.text.toString().trim())
                if (myMovie.name.isNotEmpty() && myMovie.about.isNotEmpty() && myMovie.date.isNotEmpty() && myMovie.author.isNotEmpty()) {
                    list = MySharedPreference.movieLIst
                    list.add(myMovie)
                    Toast.makeText(this@AddActivity, "${binding.edtName.text} tahrirlandi", Toast.LENGTH_SHORT).show()
                    MySharedPreference.movieLIst = list
                    finish()
                } else {
                    Toast.makeText(this@AddActivity, "Malumotlarni to'ldiring", Toast.LENGTH_SHORT).show()
                }
                if (list.contains(MyMovie(edtName.text.toString().trim(), edtAuthor.text.toString().trim(),edtAbout.text.toString().trim(), edtDate.text.toString().trim())))
                    if (position != null) {
                        list.removeAt(position.toInt())
                        MySharedPreference.movieLIst = list }
            }
        }
    }
}
