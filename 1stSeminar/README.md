# 1st Seminar Assignment

![image](https://user-images.githubusercontent.com/38918396/80783643-60f17600-8bb5-11ea-8bb3-06722b18713d.png)

### [기본과제1] ConstraintLayout 심화 학습
* __ConstraintDimentionRatio__<br>
비율로 크기를 정하려면, width/height를 0dp(or match_constraint)로 지정하고 layout_constraintDimensionRatio 속성을 이용해 수평:수직 비율값을 준다.
```xml
<ImageView
    android:layout_width="0dp"
    android:layout_height="0dp"
    app:layout_constraintDimensionRatio="1:1"
    ... />
```

* __Guideline__<br>
Guideline은 뷰를 배치하기 위해 뷰그룹 내에 설정할 수 있는 일종의 기준선이다.
TextView의 layout_constraintEnd_toEndOf 속성을 guideline에 맞춰주어야 해당 가이드라인에 맞게 적용된다.
```xml
 <TextView
    ...
    app:layout_constraintEnd_toEndOf="@+id/guideline1" />

<androidx.constraintlayout.widget.Guideline
    android:id="@+id/guideline1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    app:layout_constraintGuide_end="100dp" />
```
<br>

### [기본과제2] 회원가입 및 로그인 기능 구현하기
:memo: 회원가입 완료 시 LoginActivity로 돌아오고 , 회원가입 성공한 id와 pw가 입력되어 있도록 구현
* __startActivityForResult__<br>
startActivityForResult는 이동된 Activity로부터 값을 가져올때 쓸 수 있다.
1. _LoginActivity.kt_ - 값을 받아오고자하는 Activity 호출<br>
```kotlin
var intent = Intent(this, RegisterActivity::class.java)
startActivityForResult(intent, REQUEST_CODE)
```
2. _RegisterActivity.kt_ - 호출된 Activity<br>
(1) intent.putExtra(key, value) - 전달하고자 하는 값<br>
(2) setResult() - RESULT_OK와 값이 담긴 intent를 전달<br>
(3) finish() - Activity를 종료하고 다시 LoginActivity로 돌아감<br>
```kotlin
var intent = Intent(this, LoginActivity::class.java)
intent.putExtra("id", et_id.text.toString())
intent.putExtra("pass", et_pass.text.toString())
setResult(RESULT_OK, intent)
finish()
```

3. _LoginActivity.kt_ - onActivityResult에서 받아온 값 확인
```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_CODE) {
        if (resultCode == Activity.RESULT_OK) {
            val email = data!!.getStringExtra("email")
            val pass = data!!.getStringExtra("pass")
            et_id.setText(email)
            et_pass.setText(pass)
            ...
        }
    }
}
```
<br>

### [성장과제2] 자동 로그인 구현하기
:memo: 회원가입 후 LoginActivity로 돌아와 가입한 id, pw로 자동 로그인<br>
:memo: 로그인하여 MainActivity로 간 경우 앱을 종료했다가 재시작 시 LoginActivity에서 자동 로그인
* __SharedPreferences__<br>
SharedPreferences는 간단한 값 저장에 사용하기 좋다. (초기 설정값, 자동 로그인 등)
1. SharedPreferences 생성<br>
```kotlin
val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
val editor : SharedPreferences.Editor = prefs.edit() // 데이터 기록을 위한 editor
```
2. 데이터 저장
```kotlin
editor.putString(key, value)
editor.commit() // 필수
```
3. 저장된 데이터 불러오기
```kotlin
prefs.getString(key, "")
```
4. 모든 데이터 삭제
```kotlin
editor.clear()
editor.commit()
```
<br>

* __자동로그인__<br>
```kotlin
// SharedPreferences 안에 값이 저장되어 있지 않을 때 -> Login
if(MySharedPreferences.getUserMail(this).isNullOrBlank()
    || MySharedPreferences.getUserPass(this).isNullOrBlank()) {
    Login()
}
else { // SharedPreferences 안에 값이 저장되어 있을 때 -> MainActivity
    Toast.makeText(this, "${MySharedPreferences.getUserMail(this)}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
    finish()
}
```
