package com.example.mysecondapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mysecondapplication.ui.main.hospital.all.AllHosFragment
import com.example.mysecondapplication.ui.main.hospital.my.MyHosFragment

class HosViewPagerAdapter(fragmentManager: FragmentManager?, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager!!, lifecycle) {

    //tabLayout 아이템 갯수
    override fun getItemCount(): Int = 2

    //viewpager 프래그먼트트
   override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return MyHosFragment()
            1 -> return AllHosFragment()
        }

        return AllHosFragment()
    }
}