package com.example.a3rdseminar_practice.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> InstaFragment()
            1 -> WebtoonFragment()
            2 -> ChickenFragment()
            3 -> BookFragment()
            else -> MyinfoFragment()
        }
    }

    override fun getCount() = 5

}