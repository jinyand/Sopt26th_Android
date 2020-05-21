package com.example.a3rdseminar_practice.recycler.book

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a3rdseminar_practice.R
import com.example.a3rdseminar_practice.data.ResponseBookData

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val img_book = itemView.findViewById<ImageView>(R.id.img_book)
    val tv_booktitle = itemView.findViewById<TextView>(R.id.tv_booktitle)
    val tv_contents = itemView.findViewById<TextView>(R.id.tv_contents)
    val tv_author = itemView.findViewById<TextView>(R.id.tv_author)

    fun bind(Data: ResponseBookData) {
        Glide.with(itemView).load(Data.thumbnail).into(img_book)
        tv_booktitle.text = Data.title
        tv_contents.text = Data.contents
        tv_author.text = Data.authors[0]
    }
}