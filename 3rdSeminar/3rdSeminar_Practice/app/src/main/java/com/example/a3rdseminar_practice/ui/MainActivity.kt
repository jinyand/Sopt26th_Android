package com.example.a3rdseminar_practice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.a3rdseminar_practice.R
import com.example.a3rdseminar_practice.data.MySharedPreferences
import com.example.a3rdseminar_practice.fragment.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_viewPager.adapter =
            MainPagerAdapter(
                supportFragmentManager
            )
        main_viewPager.offscreenPageLimit = 2
        main_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                // 네비게이션 메뉴 아이템 체크상태
                // 뷰페이저의 페이지 이동 -> 하단 탭(네비게이션 메뉴)의 체크 상태 변화
                bottomNavigationView.menu.getItem(position).isChecked = true
            }

        })

        bottomNavigationView.setOnNavigationItemSelectedListener {
            // 하단 네비게이션 메뉴의 item을 선택했을 때
            when(it.itemId) {
                R.id.menu_insta -> main_viewPager.currentItem = 0
                R.id.menu_webtoon -> main_viewPager.currentItem = 1
                R.id.menu_chicken -> main_viewPager.currentItem = 2
                R.id.menu_book -> main_viewPager.currentItem = 3
                R.id.menu_myinfo -> main_viewPager.currentItem = 4
                // itemId에 따라 viewPager 바뀜 (해당하는 페이지로 이동)
            }
            true
        }

    }


}
