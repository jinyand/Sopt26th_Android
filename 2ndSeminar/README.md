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
