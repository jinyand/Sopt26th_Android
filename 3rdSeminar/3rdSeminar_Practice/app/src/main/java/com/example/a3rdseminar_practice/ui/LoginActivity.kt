package com.example.a3rdseminar_practice.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a3rdseminar_practice.R
import com.example.a3rdseminar_practice.data.MySharedPreferences
import com.example.a3rdseminar_practice.data.RequestLogin
import com.example.a3rdseminar_practice.data.ResponseLogin
import com.example.a3rdseminar_practice.network.RequestToServer
import com.example.a3rdseminar_practice.network.customEnqueue
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private val REQUEST_CODE = 100
    val requestToServer = RequestToServer// 싱글톤 그대로 가져옴

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // SharedPreferences 안에 값이 저장되어 있지 않을 때 -> Login
        if(MySharedPreferences.getUserId(this).isNullOrBlank()
            || MySharedPreferences.getUserPass(this).isNullOrBlank()) {
            Login()
        }
        else { // SharedPreferences 안에 값이 저장되어 있을 때 -> MainActivity로 이동
            showToast("${MySharedPreferences.getUserId(this)}님 자동 로그인 되었습니다.")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        tv_register.setOnClickListener{
            var intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    fun Login() {

        et_login_id.textChangedListener {
            if(it.isNullOrBlank()) {
                showToast("아이디를 입력해주세요")
            }
        }

        btn_login.setOnClickListener {

            if(et_login_id.text.isNullOrBlank() || et_login_pass.text.isNullOrBlank()) {
                showToast("아이디와 비밀번호를 확인하세요")
            }
            else {
                // 로그인 요청
                requestToServer.service.requestLogin(
                    RequestLogin(
                        id = et_login_id.text.toString(),
                        password = et_login_pass.text.toString()
                    ) // 로그인 정보를 전달
                ).customEnqueue(
                    onError = {showToast("올바르지 못한 요청입니다")},
                    onSuccess = {
                        if(it.success) {
                            MySharedPreferences.setUserId(this@LoginActivity, et_login_id.text.toString())
                            MySharedPreferences.setUserPass(this@LoginActivity, et_login_pass.text.toString())
                            showToast("${MySharedPreferences.getUserId(this@LoginActivity)}님 로그인 되었습니다.")
                            var intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            showToast("아이디와 비밀번호를 확인하세요")
                        }
                    }
                )

            }

        }

    }

    // onActivityResult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val id = data?.getStringExtra("id")
                val pass = data?.getStringExtra("pass")
                et_login_id.setText(id)
                et_login_pass.setText(pass)
                Login() // RegisterActivity에서 받아와 입력된 값을 가지고 로그인
            }
        }
    }

}