package com.example.a3rdseminar_practice.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.a3rdseminar_practice.R
import com.example.a3rdseminar_practice.data.ResponseBook
import com.example.a3rdseminar_practice.data.ResponseBookData
import com.example.a3rdseminar_practice.network.RequestBookToServer
import com.example.a3rdseminar_practice.recycler.RecyclerDecoration
import com.example.a3rdseminar_practice.recycler.book.BookAdapter
import com.example.a3rdseminar_practice.ui.textChangedListener
import kotlinx.android.synthetic.main.fragment_book.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class BookFragment : Fragment() {

    lateinit var bookAdapter: BookAdapter
    val datas = mutableListOf<ResponseBookData>()
    val requestToBookServer = RequestBookToServer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_book.addItemDecoration(
            RecyclerDecoration(20)
        )

        btn_search.setOnClickListener{
            if(et_search.text.isNullOrBlank()) {
                Toast.makeText(view.context, "책 제목을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                datas.clear()
                loadDatas()
            }
        }

    }

    fun loadDatas() {
        requestToBookServer.service.requestBook(
            title = et_search.text.toString(),
            key = getString(R.string.kakaoAPI)
        ).enqueue(object : Callback<ResponseBook>{
            override fun onFailure(call: Call<ResponseBook>, t: Throwable) {
                // 통신 실패
                Log.e("통신 실패", t.toString())
            }
            override fun onResponse(
                call: Call<ResponseBook>,
                response: Response<ResponseBook>
            ) {
                // 통신 성공
                if(response.isSuccessful) {
                    bookAdapter  = BookAdapter(view!!.context, response!!.body()!!.documents)
                    rv_book.adapter = bookAdapter
                    bookAdapter.notifyDataSetChanged()
                } else {
                    Log.e("통신 오류", response.message())
                }
            }
        })

    }

}