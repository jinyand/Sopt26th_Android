package com.example.a3rdseminar_practice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a3rdseminar_practice.SharedPreferences.MySharedPreferences
import com.example.a3rdseminar_practice.data.RequestLogin
import com.example.a3rdseminar_practice.data.ResponseLogin
import com.example.a3rdseminar_practice.network.RequestToServer
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
            Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
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

        btn_login.setOnClickListener {

            if(et_login_id.text.isNullOrBlank() || et_login_pass.text.isNullOrBlank()) {
                Toast.makeText(this, "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show()
            }
            else {
                // 로그인 요청
                requestToServer.service.requestLogin(
                    RequestLogin(
                        id = et_login_id.text.toString(),
                        password = et_login_pass.text.toString()
                    ) // 로그인 정보를 전달
                ).enqueue(object : Callback<ResponseLogin>{ // Callback 등록
                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        // 통신 실패
                    }
                    override fun onResponse(
                        call: Call<ResponseLogin>,
                        response: Response<ResponseLogin>
                    ) {
                        // 통신 성공
                        if(response.isSuccessful) { // statusCode가 200-300 사이일 떄. 응답 body 이용 가능
                            if(response.body()!!.success) { // ResponseLogin의 Success가 true일 경우
                                MySharedPreferences.setUserId(this@LoginActivity, et_login_id.text.toString())
                                MySharedPreferences.setUserPass(this@LoginActivity, et_login_pass.text.toString())
                                Toast.makeText(this@LoginActivity, "${MySharedPreferences.getUserId(this@LoginActivity)}님 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                                var intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@LoginActivity, "아이디와 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                })
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