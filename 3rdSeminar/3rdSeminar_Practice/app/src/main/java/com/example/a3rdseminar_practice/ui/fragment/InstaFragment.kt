package com.example.a3rdseminar_practice.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.a3rdseminar_practice.R
import com.example.a3rdseminar_practice.recycler.RecyclerDecoration
import com.example.a3rdseminar_practice.recycler.insta.InstaAdapter
import com.example.a3rdseminar_practice.recycler.insta.InstaData
import kotlinx.android.synthetic.main.fragment_insta.*

/**
 * A simple [Fragment] subclass.
 */
class InstaFragment : Fragment() {
    lateinit var instaAdapter: InstaAdapter
    val datas = mutableListOf<InstaData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instaAdapter = InstaAdapter(view.context)
        rv_insta.adapter = instaAdapter // 리사이클러뷰의 어댑터를 instaAdapter로 지정해줌
        rv_insta.addItemDecoration(
            RecyclerDecoration(
                20
            )
        )
        loadDatas() // 데이터를 임의로 생성하고 어댑터에 전달
    }

    private fun loadDatas() {
        datas.apply {
            add(
                InstaData(
                    userName = "jinyand",
                    img_profile = "https://cdn.pixabay.com/photo/2020/04/21/23/07/cat-family-5074959__340.jpg",
                    img_contents = "https://cdn.pixabay.com/photo/2020/04/22/13/57/squirrel-5078283__340.jpg"
                )
            )
            add(
                InstaData(
                    userName = "안드로이드",
                    img_profile = "https://cdn.pixabay.com/photo/2020/03/28/15/20/cat-4977436__340.jpg",
                    img_contents = "https://cdn.pixabay.com/photo/2020/04/06/14/15/landscape-5009868__340.jpg"
                )
            )
            add(
                InstaData(
                    userName = "세미나",
                    img_profile = "https://cdn.pixabay.com/photo/2020/04/07/17/01/chicks-5014152__340.jpg",
                    img_contents = "https://cdn.pixabay.com/photo/2020/04/19/09/07/fantasy-5062586__340.jpg"
                )
            )
            instaAdapter.datas = datas
            instaAdapter.notifyDataSetChanged() // 데이터가 갱신됨을 어댑터에 알려주는 역할
        }
    }
}
