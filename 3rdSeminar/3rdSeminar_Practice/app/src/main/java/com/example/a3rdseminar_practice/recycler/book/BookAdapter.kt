package com.example.a3rdseminar_practice.recycler.book

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a3rdseminar_practice.R
import com.example.a3rdseminar_practice.data.ResponseBook
import com.example.a3rdseminar_practice.data.ResponseBookData

class BookAdapter(private val context : Context, val datas: List<ResponseBookData>) : RecyclerView.Adapter<BookViewHolder>() {

    //var datas = mutableListOf<ResponseBookData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}