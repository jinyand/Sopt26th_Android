package com.example.a3rdseminar_practice.ui

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.textChangedListener(textChanged : (CharSequence?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher{
        override fun afterTextChanged(s: Editable?) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textChanged(s) // s에 대한 정보를 textChanged라는 함수의 매개변수로 받아와 넘겨줌
        }

    })
}