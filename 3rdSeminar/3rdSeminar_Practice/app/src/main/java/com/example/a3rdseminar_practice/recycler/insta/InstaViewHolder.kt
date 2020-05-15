package com.example.a3rdseminar_practice.recycler.insta

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a3rdseminar_practice.R

class InstaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val tv_username = itemView.findViewById<TextView>(R.id.tv_username)
    val img_profile = itemView.findViewById<ImageView>(R.id.img_profile)
    val img_contents = itemView.findViewById<ImageView>(R.id.img_contents)

    fun bind(instaData: InstaData) {
        // ViewHolder와 instaData 클래스의 각 변수를 연동하는 역할
        tv_username.text = instaData.userName
        Glide.with(itemView).load(instaData.img_profile).into(img_profile)
        Glide.with(itemView).load(instaData.img_contents).into(img_contents)
    }
}