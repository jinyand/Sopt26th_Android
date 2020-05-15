package com.example.a3rdseminar_practice.recycler.insta

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a3rdseminar_practice.R

class InstaAdapter(private val context : Context) : RecyclerView.Adapter<InstaViewHolder>() {
    var datas = mutableListOf<InstaData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstaViewHolder {
        // LayoutInflater를 이용하여 item_insta.xml을 inflate 시킨다.
        // (참고) inflate란 ? xml에 쓰여있는 view의 정의를 실제 view객체로 만드는 역할
        val view = LayoutInflater.from(context).inflate(R.layout.item_insta, parent, false)
        return InstaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: InstaViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}