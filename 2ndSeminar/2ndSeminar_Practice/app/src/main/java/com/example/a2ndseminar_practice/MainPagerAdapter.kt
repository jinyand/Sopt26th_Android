package com.example.a2ndseminar_practice

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.a2ndseminar_practice.fragment.HomeFragment
import com.example.a2ndseminar_practice.fragment.LibraryFragment
import com.example.a2ndseminar_practice.fragment.MypageFragment

class MainPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> LibraryFragment()
            else -> MypageFragment()
        }
    }

    override fun getCount() = 3

}