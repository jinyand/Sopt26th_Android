# 2nd Seminar Assignment

### [기본과제1] Bottom Navigation, ViewPager 실습
* __Bottom Navigation__<br>
BottomNavigationView는 화면 하단에 포함되는 View이며, 크게 2가지 View 정의가 필요하다.<br>
(1) BottomNavigationView에서 사용할 메뉴를 나타내는 navigation.xml<br>
(2) BottomNavigationView를 사용할 layout에서 BottomNavigationView 정의
```xml
<com.google.android.material.bottomnavigation.BottomNavigationView
    ...
    app:itemIconTint="@color/bottom_selector"
    app:itemTextColor="@color/bottom_selector"
    app:menu="@menu/navigation"/>
```

* __ViewPager__<br>
ViewPager는 데이터를 페이지 단위로 표시하고 화면을 쓸어 넘기는 동작인 스와이프(Swipe)를 통해 페이지 전환을 할 수 있는 컨테이너(Container)이다. ViewPager의 사용 방식은 다음과 같다.<br>
(1) 먼저 원하는 페이지 개수만큼 Fragment를 생성한다.<br>
(2) ViewPager에서 이용하는 페이지 뷰(View)를 생성해주는 Adapter를 만든다.<br>
(3) Adapter를 통해 Fragment를 ViewPager에 등록한다.<br>

* __Fragment__<br>
Fragment는 액티비티 내에서 화면 UI 일부를 나타낸다.<br>
여러 개의 프래그먼트를 조합하여 액티비티가 출력하는 한 화면의 UI를 표현할 수 있다.<br>
액티비티 실행 중에도 화면에 동적으로 추가되거나 다른 프래그먼트로 교체가 가능하다.

* __PagerAdapter__<br>
PagerAdapter는 ViewPager에서 이용하는 페이지 뷰를 생성하기 위한 용도의 어뎁터이다.<br>
Adapter가 있어야 fragment와 ViewPager사이의 연결이 가능하므로 필수로 구현해야 한다.

1. PagerAdapter를 생성한다.
```kotlin
class MainPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> LibraryFragment()
            else -> MypageFragment()
        }
    }
    override fun getCount() = 3 // 전체 페이지 수
}
```

2. ViewPager에서 사용하여 fragment와 연결한다.
```kotlin
main_viewPager.adapter = MainPagerAdapter(supportFragmentManager)
main_viewPager.offscreenPageLimit = 2
```

3. BottomNavigation의 메뉴를 클릭했을 때 해당하는 프래그먼트가 호출되어 화면에 표시되도록 한다.
```kotlin
main_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
    ...
     override fun onPageSelected(position: Int) {
        bottomNavigationView.menu.getItem(position).isChecked = true
        // 뷰페이저의 페이지 이동 -> 하단 탭(네비게이션 메뉴)의 체크 상태 변화
    }
})

bottomNavigationView.setOnNavigationItemSelectedListener {
    // 하단 네비게이션 메뉴의 item을 선택했을 때 -> 해당하는 페이지로 이동
    when(it.itemId) {
        R.id.menu_home -> main_viewPager.currentItem = 0
        R.id.menu_book -> main_viewPager.currentItem = 1
        R.id.menu_person -> main_viewPager.currentItem = 2
        // itemId에 따라 viewPager 바뀜
    }
    true
}
```
<br>

### [기본과제1] RecyclerView 실습
* __RecyclerView__<br>
RecyclerView는 사용자가 관리하는 많은 수의 데이터 집합(Data Set)을 개별 아이템 단위로 구성하여 화면에 출력하는 뷰그룹(ViewGroup)이며, 한 화면에 표시되기 힘든 많은 수의 데이터를 스크롤 가능한 리스트로 표시해주는 위젯이다.<br>
RecyclerView의 사용 방식은 다음과 같다.
1. __ItemView__ - 반복될 뷰를 만든다.<br>
앞으로 재사용될 레이아웃 파일을 생성한다.<br>

2. __Data class__ - 데이터 형태를 정의하는 class를 생성한다.<br>
```kotlin
data class InstaData (
    val userName : String,
    val img_profile : String,
    val img_contents : String
)
```

3. __ViewHolder__ - 받은 데이터를 뷰로 연결시켜준다.<br>
ViewHolder란 각 뷰들을 보관하는 홀더 객체이다. 각 뷰 객체를 ViewHolder에 보관함으로써 findViewById 같은 반복적으로 호출되는 메서드를 효과적으로 줄여 속도를 향상시킨다.
```kotlin
class InstaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val tv_username = itemView.findViewById<TextView>(R.id.tv_username)
    val img_profile = itemView.findViewById<ImageView>(R.id.img_profile)
    val img_contents = itemView.findViewById<ImageView>(R.id.img_contents)

    fun bind(instaData: InstaData) {
        // ViewHolder와 instaData 클래스의 각 변수를 연동하는 역할
        tv_username.text = instaData.userName
        Glide.with(itemView).load(instaData.img_profile).into(img_profile)
        Glide.with(itemView).load(instaData.img_contents).into(img_contents)
    }
}
```

4. __Adapter__ - RecyclerView에 표시될 아이템 뷰를 생성한다.<br>
Adapter는 필요에 따라 ViewHolder를 만들고, 데이터와 바인딩함으로써 ViewHolder를 특정 위치에 할당한다.
RecyclerView의 Adapter에서 꼭 구현해야 하는 것은 다음과 같다.

| 메서드 | 설명 |
|:---|:---|
| onCreateViewHolder(ViewGroup parent, int viewType) | viewType 형태의 아이템 뷰를 위한 뷰홀더 객체 생성 |
| onBindViewHolder(ViewHolder holder, int position) | position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시 |
| getItemCount() | 전체 아이템 갯수 리턴 |

```kotlin
class InstaAdapter(private val context : Context) : RecyclerView.Adapter<InstaViewHolder>() {
    var datas = mutableListOf<InstaData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstaViewHolder {
        // LayoutInflater를 이용하여 item_insta.xml을 inflate 시킨다.
        // (참고) inflate란 ? xml에 쓰여있는 view의 정의를 실제 view객체로 만드는 역할
        val view = LayoutInflater.from(context).inflate(R.layout.item_insta, parent, false)
        return InstaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: InstaViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}
```

5. __RecyclerView__ - 마지막으로 데이터를 넣고, Adapter을 이용해서 RecyclerView에 띄워준다.
```kotlin
class HomeFragment : Fragment() {

    lateinit var instaAdapter: InstaAdapter
    val datas = mutableListOf<InstaData>()

    ...

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instaAdapter = InstaAdapter(view.context)
        rv_home.adapter = instaAdapter // 리사이클러뷰의 어댑터를 instaAdapter로 지정
        loadDatas() // 데이터를 임의로 생성하고 어댑터에 전달
    }

    private fun loadDatas() {
        datas.apply {
            add(
                InstaData(
                    userName = "userName",
                    img_profile = "이미지 파일 or 링크",
                    img_contents = "이미지 파일 or 링크"
                )
            )
            ...
            instaAdapter.datas = datas
            instaAdapter.notifyDataSetChanged() // 데이터가 갱신됨을 어댑터에 알려주는 역할
        }
    }

}
```
<br>

### [기본과제2] RecyclerView의 itemDecoration, clipToPadding
* __itemDecoration__<br>
* __clipToPadding__<br>
<br>

### [성장과제1] GridLayoutManager
__LayoutManager__ - 배치 방향<br>
LinearLayoutManager : 세로/가로방향 배치<br>
GridLayoutManager : 바둑판 형식 배치<br>
