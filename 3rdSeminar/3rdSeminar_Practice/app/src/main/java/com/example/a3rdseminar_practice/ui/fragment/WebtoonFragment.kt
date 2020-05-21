package com.example.a3rdseminar_practice.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.a3rdseminar_practice.R
import com.example.a3rdseminar_practice.recycler.webtoon.ToonAdapter
import com.example.a3rdseminar_practice.recycler.webtoon.ToonData
import kotlinx.android.synthetic.main.fragment_webtoon.*

/**
 * A simple [Fragment] subclass.
 */
class WebtoonFragment : Fragment() {
    lateinit var toonAdapter : ToonAdapter
    val datas = mutableListOf<ToonData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webtoon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myLayoutManager = GridLayoutManager(view.context, 3, RecyclerView.VERTICAL,false)
        rv_webtoon.layoutManager = myLayoutManager

        val v_decoration = DividerItemDecoration(view.context, LinearLayoutManager.VERTICAL)
        rv_webtoon.addItemDecoration(v_decoration)
        val h_decoration = DividerItemDecoration(view.context, LinearLayoutManager.HORIZONTAL)
        rv_webtoon.addItemDecoration(h_decoration)

        toonAdapter = ToonAdapter(view.context)
        rv_webtoon.adapter = toonAdapter
        loadDatas()
    }

    private fun loadDatas() {
        datas.apply {
            add(
                ToonData(
                    img_toon = "https://cdn.pixabay.com/photo/2019/05/04/15/24/art-4178302__340.jpg",
                    txt_title= "웹툰1",
                    txt_score = "★ 9.94",
                    txt_name = "SIU"
                )
            )
            add(
                ToonData(
                    img_toon = "https://image.shutterstock.com/image-vector/set-man-thinking-over-isolated-260nw-1342667234.jpg",
                    txt_title= "웹툰2",
                    txt_score = "★ 9.89",
                    txt_name = "조용석"
                )
            )
            add(
                ToonData(
                    img_toon = "https://cdn.pixabay.com/photo/2020/04/19/09/07/fantasy-5062586__340.jpg",
                    txt_title= "웹툰3",
                    txt_score = "★ 9.80",
                    txt_name = "박태준/전선욱"
                )
            )
            add(
                ToonData(
                    img_toon = "https://cdn.pixabay.com/photo/2017/10/16/08/53/cat-2856531__340.jpg",
                    txt_title= "웹툰4",
                    txt_score = "★ 9.98",
                    txt_name = "모랑지"
                )
            )
            add(
                ToonData(
                    img_toon = "https://cdn.pixabay.com/photo/2019/12/17/14/43/illustration-4701783__340.png",
                    txt_title= "웹툰5",
                    txt_score = "★ 9.85",
                    txt_name = "영파카"
                )
            )
            add(
                ToonData(
                    img_toon = "https://cdn.pixabay.com/photo/2019/12/25/18/07/snow-globe-4719039__340.jpg",
                    txt_title= "웹툰6",
                    txt_score = "★ 9.98",
                    txt_name = "QTT"
                )
            )
            add(
                ToonData(
                    img_toon = "https://cdn.pixabay.com/photo/2019/05/04/15/24/art-4178302__340.jpg",
                    txt_title= "웹툰7",
                    txt_score = "★ 9.94",
                    txt_name = "SIU"
                )
            )
            add(
                ToonData(
                    img_toon = "https://image.shutterstock.com/image-vector/set-man-thinking-over-isolated-260nw-1342667234.jpg",
                    txt_title= "웹툰8",
                    txt_score = "★ 9.89",
                    txt_name = "조용석"
                )
            )
            add(
                ToonData(
                    img_toon = "https://cdn.pixabay.com/photo/2020/04/19/09/07/fantasy-5062586__340.jpg",
                    txt_title= "웹툰9",
                    txt_score = "★ 9.80",
                    txt_name = "박태준/전선욱"
                )
            )
            add(
                ToonData(
                    img_toon = "https://cdn.pixabay.com/photo/2017/10/16/08/53/cat-2856531__340.jpg",
                    txt_title= "웹툰10",
                    txt_score = "★ 9.98",
                    txt_name = "모랑지"
                )
            )
            add(
                ToonData(
                    img_toon = "https://cdn.pixabay.com/photo/2019/12/17/14/43/illustration-4701783__340.png",
                    txt_title= "웹툰11",
                    txt_score = "★ 9.85",
                    txt_name = "영파카"
                )
            )
            add(
                ToonData(
                    img_toon = "https://cdn.pixabay.com/photo/2019/12/25/18/07/snow-globe-4719039__340.jpg",
                    txt_title= "웹툰12",
                    txt_score = "★ 9.98",
                    txt_name = "QTT"
                )
            )
            toonAdapter.datas = datas
            toonAdapter.notifyDataSetChanged()
        }
    }
}
