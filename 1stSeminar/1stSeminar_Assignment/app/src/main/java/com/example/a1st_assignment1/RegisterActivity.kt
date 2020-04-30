package com.example.a1st_assignment1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.et_id
import kotlinx.android.synthetic.main.activity_register.et_pass

class RegisterActivity : AppCompatActivity() {

    private lateinit var email: String
    private lateinit var pass: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register.setOnClickListener{

            email = et_id.text.toString()
            pass = et_pass.text.toString()

            if(et_id.text.isNullOrBlank() || et_pass.text.isNullOrBlank()) {
                Toast.makeText(this, "이메일과 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                if(et_pass.text.toString() == et_passcheck.text.toString()) {
                    Toast.makeText(this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("email", email)
                    intent.putExtra("pass", pass)
                    setResult(RESULT_OK, intent)

                    MySharedPreferences.setUserMail(this, email)
                    MySharedPreferences.setUserPass(this, pass)
                    finish()
                } else {
                    Toast.makeText(this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }

            }


        }
    }
}
