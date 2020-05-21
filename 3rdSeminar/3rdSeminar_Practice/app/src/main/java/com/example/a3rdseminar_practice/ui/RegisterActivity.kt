package com.example.a3rdseminar_practice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a3rdseminar_practice.R
import com.example.a3rdseminar_practice.data.MySharedPreferences
import com.example.a3rdseminar_practice.data.RequestRegister
import com.example.a3rdseminar_practice.data.ResponseRegister
import com.example.a3rdseminar_practice.network.RequestToServer
import com.example.a3rdseminar_practice.network.customEnqueue
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.et_reg_id
import kotlinx.android.synthetic.main.activity_register.et_reg_pass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var id: String
    private lateinit var pass: String
    val requestToServer = RequestToServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register.setOnClickListener{

            id = et_reg_id.text.toString()
            pass = et_reg_pass.text.toString()

            if(et_reg_id.text.isNullOrBlank() || et_reg_pass.text.isNullOrBlank() || et_reg_passcheck.text.isNullOrBlank() ||
                    et_reg_name.text.isNullOrBlank() || et_reg_mail.text.isNullOrBlank() || et_reg_phone.text.isNullOrBlank()) {
                showToast("가입 정보를 모두 작성해주세요.")
            }
            else {
                if(pass == et_reg_passcheck.text.toString()) {
                    requestToServer.service.requestRegister(
                        RequestRegister(
                            id = et_reg_id.text.toString(),
                            password = et_reg_pass.text.toString(),
                            name = et_reg_name.text.toString(),
                            email = et_reg_mail.text.toString(),
                            phone = et_reg_phone.text.toString()
                        ) // 회원가입 정보를 전달
                    ).customEnqueue(
                        onError = {showToast("올바르지 못한 요청입니다")},
                        onSuccess = {
                            if(it.status == 200) {
                                showToast("이미 존재하는 아이디입니다.")
                            }
                            else if(it.success) {
                                showToast("회원가입 되었습니다.")
                                var intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                intent.putExtra("id", id)
                                intent.putExtra("pass", pass)
                                setResult(RESULT_OK, intent)

                                MySharedPreferences.setUserId(this@RegisterActivity, id)
                                MySharedPreferences.setUserPass(this@RegisterActivity, pass)
                                finish()
                            }
                            else {
                                showToast("가입 정보를 모두 작성해주세요.")
                            }
                        }
                    )
                } else {
                    showToast("비밀번호를 다시 확인해주세요.")
                }

            }


        }
    }
}
