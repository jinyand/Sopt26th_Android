package com.example.a1st_assignment1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {

            if(et_id.text.isNullOrBlank() && et_pass.text.isNullOrBlank()) {
                Toast.makeText(this, "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show()
            }
            else {
                MySharedPreferences.setUserMail(this, et_id.text.toString())
                MySharedPreferences.setUserPass(this, et_id.text.toString())
                Toast.makeText(this, "자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }

        tv_register.setOnClickListener{
            var intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    fun autoLogin() {

        if(!(MySharedPreferences.getUserMail(this).isNullOrBlank() ||
                    MySharedPreferences.getUserPass(this).isNullOrBlank())) {
            btn_login.setOnClickListener {
                Toast.makeText(this, "자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        } else {
            Toast.makeText(this, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                val email = data!!.getStringExtra("email")
                val pass = data!!.getStringExtra("pass")
                et_id.setText(email)
                et_pass.setText(pass)
                autoLogin()
            }
        }
    }

}