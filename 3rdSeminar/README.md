# 3rd Seminar Assignment

### [기본과제 1] 로그인, 회원가입 부분 Retrofit을 이용하여 서버와 통신하기

### :bulb: JSON
* 텍스트로 이루어져 있어 기계, 사람 모두 이해하기 쉽다.
* 프로그래밍 언어와 플랫폼에 독립적 이기 때문에 서로 다른 시스템간에 객체를 교환하기 좋다.
* JSON 구조
  * JSON 데이터는 이름과 값의 쌍으로 이루어집니다.
  * JSON 데이터는 쉼표(,)로 나열됩니다.
  * 객체(object)는 중괄호({})로 둘러쌓아 표현합니다.
  * 배열(array)은 대괄호([])로 둘러쌓아 표현합니다.
  
### :bulb: 동기와 비동기
* 동기
  * 직렬적으로 태스크를 수행한다.
  * 어떤 작업이 수행 중이면 다음 작업은 대기하게 된다  .
  Ex) 서버에서 데이터를 가져와 화면에 표시하는 작업 수행할 때 서버에 데이터를 요청하고 데이터가 응답될 때까지 이후 태스크들은 블로킹 작업 중단 된다.
  ![image](https://user-images.githubusercontent.com/38918396/82306013-7100b680-99f9-11ea-8bb4-d5cf8ae581f9.png)
* 비동기
  * 병렬적으로 태스크를 수행한다.
  * 어떤 작업이 수행 중이더라도 다음 태스크를 실행한다.  
  Ex) 서버에서 데이터를 가져와 화면에 표시하는 작업 수행할 때 서버에 데이터를 요청하고 데이터가 응답될 때까지 대기하지 않고 즉시 다음 태스크를 수행한다. 이후 서버로부터 데이터가 응답되면 이벤트가 발생한다.
  ![image](https://user-images.githubusercontent.com/38918396/82306219-b7eeac00-99f9-11ea-8f6b-2ffc045114c7.png)
  
### :bulb: Retrofit의 활용
* Retrofit 공식 문서 http://devflow.github.io/retrofit-kr/  
1. 라이브러리 추가
  * Retrofit 라이브러리 : https://github.com/square/retrofit  
  `implementation 'com.squareup.retrofit2:retrofit:2.6.2`  
  * Retrofit 라이브러리 응답으로 가짜 객체를 만들기 위해  
  `implementation 'com.squareup.retrofit2:retrofit-mock:2.6.2`
  * 객체 시리얼라이즈를 위한 Gson 라이브러리 : https://github.com/google/gson  
  `implementation 'com.google.code.gson:gson:2.8.6`
  * Retrofit 에서 Gson 을 사용하기 위한 라이브러리  
  `implementation 'com.squareup.retrofit2:converter-gson:2.6.2`

2. API에 따른 Request / Response 객체 설계
  
< RequestResister.kt >
```kotlin
data class RequestRegister(
    val id : String,
    val password : String,
    val name : String,
    val email : String,
    val phone : String
)
```
< ResponseRegister.kt >
  ```kotlin
  data class ResponseRegister (
    val status : Int,
    val success : Boolean,
    val message : String
  )
  ```

3. Retrofit Interface 설계

< RequestInterface.kt >
```kotlin
interface RequestInterface{
    @POST("/user/signup")
    fun requestRegister(@Body body : RequestRegister) : Call<ResponseRegister>
}
```

4. Retrofit Interface 실제 구현체 만들기
  
< RequestToServer.kt >
```kotlin
object RequestToServer {
// object : 싱글톤
    var retrofit = Retrofit.Builder()
        .baseUrl("baseUrl")
        .addConverterFactory(GsonConverterFactory.create())
        // Retrofit으로 받아온 json 데이터를 데이터클래스로 변환하기 쉽게 해줌
        .build()

    var service: RequestInterface = retrofit.create(RequestInterface::class.java)
    // retrofit 객체의 create 호출
    // Interface 클래스 타입을 넘겨 실제 구현체를 만들어준다.
}
```

5. Callback 등록, 통신 요청
  * `Call<Type>` : 비동기적으로 Type 을 받아오는 객체
  * `Callback<Type>` : Type 객체를 받아왔을 때, 프로그래머가 할 행동
  
(1) Call 타입이 리턴됨
```kotlin
 requestToServer.service.requestRegister(
    RequestRegister(
        id = et_reg_id.text.toString(),
        password = et_reg_pass.text.toString(),
        name = et_reg_name.text.toString(),
        email = et_reg_mail.text.toString(),
        phone = et_reg_phone.text.toString()
        ) // 회원가입 정보를 전달
    )
```
(2) 실제 서버 통신을 비동기적으로 요청 & 응답을 받았을 경우 수행할 행동
```kotlin
.enqueue(object : Callback<ResponseRegister> { // Callback 등록
    override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
    // 통신 실패 : 비동기 통신 중 발생한 에러 처리하는 부분
    }
    override fun onResponse(
        call: Call<ResponseRegister>,
        response: Response<ResponseRegister>
    ) {
        // 통신 성공
        if(response.body()!!.status == 200) {
            // status code가 200일 때 : 아이디 중복
        } else if(response.body()!!.success) {
            // succeess가 true일 때 : 회원가입 성공
        } else {
            // 이외의 경우 (데이터 누락 등)
        }
    }
})
```


### [기본과제 2] 1·2·3차 세미나를 하나의 프로젝트로 합치기
