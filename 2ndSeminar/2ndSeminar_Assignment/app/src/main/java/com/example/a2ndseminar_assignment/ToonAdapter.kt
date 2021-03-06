package com.example.a2ndseminar_assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToonAdapter(private val context : Context) : RecyclerView.Adapter<ToonViewHolder>() {
    var datas = mutableListOf<ToonData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToonViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_webtoon, parent, false)
        return ToonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ToonViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}