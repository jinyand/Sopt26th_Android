# 4th Seminar Assignment

* 4차 세미나의 코드는 [3차 세미나](https://github.com/jinyand/Sopt26th_Android/tree/master/3rdSeminar)에 통합되어 있음

### :bulb: Kotlin - 확장함수의 활용
* 확장함수 : 어떤 클래스의 멤버 메소드인 것처럼 호출할 수 있지만 그 클래스의 밖에 선언된 함수
* 코틀린은 확장 함수 기능을 사용하여 쉽게 기존 클래스에 함수를 추가할 수 있다.

EX) Toast 띄우기
```kotlin
Toast.makeText(view.context, "책 제목을 입력해주세요", Toast.LENGTH_SHORT).show()
```
⬇
```kotlin
// showToast.kt
fun Context.showToast(msg : String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
```
```kotlin
// 실제 사용할 때 : 훨씬 간결하게 사용할 수 있음
showToast("message")
```

EX) addTextChangedListener 텍스트 입력 이벤트 처리
* 코틀린에서는 함수를 매개변수로 넘길 수 있다.
* 아래 예제에서 textChanged라는 함수를 통해 CharSequence가 매개변수로 넘어와 사용된다.
```kotlin
fun EditText.textChangedListener(textChanged : (CharSequence?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher{
        override fun afterTextChanged(s: Editable?) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textChanged(s) // s에 대한 정보를 textChanged 함수의 매개변수로 받아와 넘겨준다
        }

    })
}
```
<br>

### :bulb: Kotlin - 라이브 템플릿
* Settings - Editor - Live Templates 에서 추가할 수 있다.
* 라이브 템플릿으로 불러와서 다시 입력해주어야하는 부분은 $className$, $viewHolder$와 같은 형식으로 작성한다.
