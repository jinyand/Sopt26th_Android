package com.example.a2ndseminar_assignment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ToonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val img_toon = itemView.findViewById<ImageView>(R.id.img_toon)
    val txt_title = itemView.findViewById<TextView>(R.id.txt_title)
    val txt_score = itemView.findViewById<TextView>(R.id.txt_score)
    val txt_name = itemView.findViewById<TextView>(R.id.txt_name)

    fun bind(toonData: ToonData) {
        Glide.with(itemView).load(toonData.img_toon).into(img_toon)
        txt_title.text = toonData.txt_title
        txt_score.text = toonData.txt_score
        txt_name.text = toonData.txt_name
    }
}