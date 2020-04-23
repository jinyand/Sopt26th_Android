package com.example.a1st_assignment1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            if(et_id.text.isNullOrBlank()||et_pass.text.isNullOrBlank()) {
                Toast.makeText(this, "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show()
            } else {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        tv_register.setOnClickListener{
            var intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                val resultId = data!!.getStringExtra("id").toString()
                val resultPass = data!!.getStringExtra("pass").toString()
                et_id.setText(resultId)
                et_pass.setText(resultPass)
            }
        }
    }
}
