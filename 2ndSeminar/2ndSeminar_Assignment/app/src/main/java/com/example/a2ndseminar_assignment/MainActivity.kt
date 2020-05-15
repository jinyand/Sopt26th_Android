package com.example.a2ndseminar_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout.VERTICAL
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toonAdapter : ToonAdapter
    val datas = mutableListOf<ToonData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myLayoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL,false)
        rv_toon.layoutManager = myLayoutManager

        val v_decoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        rv_toon.addItemDecoration(v_decoration)
        val h_decoration = DividerItemDecoration(applicationContext, LinearLayoutManager.HORIZONTAL)
        rv_toon.addItemDecoration(h_decoration)

        toonAdapter = ToonAdapter(this)
        rv_toon.adapter = toonAdapter
        loadDatas()
    }

    private fun loadDatas() {
        datas.apply {
            add(
                ToonData(
                    img_toon = "https://cdn.pixabay.com/photo/2020/05/05/23/08/africa-5135407__340.jpg",
                    txt_title= "신의 탑",
                    txt_score = "★ 9.94",
                    txt_name = "SIU"
                )
            )
            add(
                ToonData(
                    img_toon = "https://comic.naver.com/webtoon/list.nhn?titleId=602910&weekday=mon",
                    txt_title= "윈드브레이커",
                    txt_score = "★ 9.89",
                    txt_name = "조용석"
                )
            )
            add(
                ToonData(
                    img_toon = "https://comic.naver.com/webtoon/list.nhn?titleId=733766&weekday=mon",
                    txt_title= "인생존망",
                    txt_score = "★ 9.80",
                    txt_name = "박태준/전선욱"
                )
            )
            add(
                ToonData(
                    img_toon = "https://comic.naver.com/webtoon/list.nhn?titleId=654774&weekday=mon",
                    txt_title= "소녀의 세계",
                    txt_score = "★ 9.98",
                    txt_name = "모랑지"
                )
            )
            add(
                ToonData(
                    img_toon = "https://comic.naver.com/webtoon/list.nhn?titleId=597478&weekday=mon",
                    txt_title= "평범한 8반",
                    txt_score = "★ 9.85",
                    txt_name = "영파카"
                )
            )
            add(
                ToonData(
                    img_toon = "https://comic.naver.com/webtoon/list.nhn?titleId=694946&weekday=mon",
                    txt_title= "귀전구담",
                    txt_score = "★ 9.98",
                    txt_name = "QTT"
                )
            )
            add(
                ToonData(
                    img_toon = "https://cdn.pixabay.com/photo/2020/03/26/22/57/hand-sanitizer-4972049__340.png",
                    txt_title= "신의 탑",
                    txt_score = "★ 9.94",
                    txt_name = "SIU"
                )
            )
            add(
                ToonData(
                    img_toon = "https://comic.naver.com/webtoon/list.nhn?titleId=602910&weekday=mon",
                    txt_title= "윈드브레이커",
                    txt_score = "★ 9.89",
                    txt_name = "조용석"
                )
            )
            add(
                ToonData(
                    img_toon = "https://comic.naver.com/webtoon/list.nhn?titleId=733766&weekday=mon",
                    txt_title= "인생존망",
                    txt_score = "★ 9.80",
                    txt_name = "박태준/전선욱"
                )
            )
            add(
                ToonData(
                    img_toon = "https://comic.naver.com/webtoon/list.nhn?titleId=654774&weekday=mon",
                    txt_title= "소녀의 세계",
                    txt_score = "★ 9.98",
                    txt_name = "모랑지"
                )
            )
            add(
                ToonData(
                    img_toon = "https://comic.naver.com/webtoon/list.nhn?titleId=597478&weekday=mon",
                    txt_title= "평범한 8반",
                    txt_score = "★ 9.85",
                    txt_name = "영파카"
                )
            )
            add(
                ToonData(
                    img_toon = "https://comic.naver.com/webtoon/list.nhn?titleId=694946&weekday=mon",
                    txt_title= "귀전구담",
                    txt_score = "★ 9.98",
                    txt_name = "QTT"
                )
            )
            toonAdapter.datas = datas
            toonAdapter.notifyDataSetChanged()
        }
    }
}