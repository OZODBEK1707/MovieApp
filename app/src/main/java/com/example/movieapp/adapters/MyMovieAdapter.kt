package com.example.movieapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ActivityMain2Binding
import com.example.movieapp.models.MyMovie
import com.example.movieapp.databinding.ItemRvBinding
import com.example.movieapp.ui.MainActivity

class MyMovieAdapters(list1: MainActivity, val list: List<MyMovie>, val rvClick: RvClick): RecyclerView.Adapter<MyMovieAdapters.Vh>(){
    inner class Vh(val  itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(myMovie: MyMovie, position: Int){
            itemRvBinding.tvName.text = myMovie.name
            itemRvBinding.tvAuthor.text = myMovie.author
            itemRvBinding.tvDate.text = myMovie.date

            itemRvBinding.btnDelete.setOnClickListener{
                rvClick.deleteMovie(myMovie, position)
            }
            itemRvBinding.btnEdit.setOnClickListener{
                rvClick.editMovie(myMovie, position)
            }
            itemRvBinding.liner1.setOnClickListener{
                rvClick.onMyLinClick(myMovie, position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface RvClick{
        fun deleteMovie(movie: MyMovie, position: Int)
        fun editMovie(movie: MyMovie, position: Int)
        fun onMyLinClick(movie: MyMovie, position: Int)
    }



}