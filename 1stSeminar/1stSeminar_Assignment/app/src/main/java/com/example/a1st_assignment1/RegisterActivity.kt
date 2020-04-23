package com.example.a1st_assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register.setOnClickListener{
            var intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id", et_id.text.toString())
            intent.putExtra("pass", et_pass.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
