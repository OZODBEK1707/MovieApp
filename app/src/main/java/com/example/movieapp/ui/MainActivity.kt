package com.example.movieapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.movieapp.DataBase.MySharedPreference
import com.example.movieapp.MainActivity2
import com.example.movieapp.MyMovieAdapters
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.models.MyMovie

class MainActivity : AppCompatActivity(), MyMovieAdapters.RvClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myMovieAdapter: MyMovieAdapters
    private lateinit var list: ArrayList<MyMovie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MySharedPreference.init(this)
            list = MySharedPreference.movieLIst
        myMovieAdapter = MyMovieAdapters(this, list, this)
        binding.rvMovies.adapter = myMovieAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        list.clear()
        list.addAll(MySharedPreference.movieLIst)
        myMovieAdapter.notifyDataSetChanged()

    }

    override fun deleteMovie(movie: MyMovie, position: Int) {
        list.removeAt(position)
        MySharedPreference.movieLIst = list
        myMovieAdapter.notifyItemRemoved(position)
        Toast.makeText(this, "${movie.name} o'chirildi", Toast.LENGTH_SHORT).show()

    }

    override fun editMovie(movie: MyMovie, position: Int) {
        MySharedPreference.movieLIst = list
        myMovieAdapter.notifyItemRemoved(position)
        val intent = Intent(this,AddActivity::class.java)
        intent.putExtra("position",position.toString())
        intent.putExtra("name",MySharedPreference.movieLIst[position].name)
        intent.putExtra("author",MySharedPreference.movieLIst[position].author)
        intent.putExtra("about",MySharedPreference.movieLIst[position].about)
        intent.putExtra("data",MySharedPreference.movieLIst[position].date)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, AddActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    override fun onMyLinClick(movie: MyMovie, position: Int) {
        val intent = Intent(this@MainActivity, MainActivity2::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
    }
