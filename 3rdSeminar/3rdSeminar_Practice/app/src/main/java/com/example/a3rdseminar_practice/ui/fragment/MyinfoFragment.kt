package com.example.a3rdseminar_practice.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a3rdseminar_practice.R
import com.example.a3rdseminar_practice.data.MySharedPreferences
import com.example.a3rdseminar_practice.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_myinfo.*

/**
 * A simple [Fragment] subclass.
 */
class MyinfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_myinfo_id.setText(MySharedPreferences.getUserId(this.activity!!)+"님 로그인 중입니다.\n로그아웃 하시겠습니까?")

        btn_logout.setOnClickListener {
            MySharedPreferences.clearUser(this.activity!!)
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }
    }

}
