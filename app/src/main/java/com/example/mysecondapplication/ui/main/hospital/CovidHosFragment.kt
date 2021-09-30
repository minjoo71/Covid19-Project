package com.example.mysecondapplication.ui.main.hospital

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.mysecondapplication.R
import com.example.mysecondapplication.adapter.HosViewPagerAdapter
import com.example.mysecondapplication.databinding.FragmentCovidHosBinding
import com.example.mysecondapplication.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.RuntimeException
import java.util.*


//선별 진료소 화면, ViewPager적용
class CovidHosFragment : BaseFragment<FragmentCovidHosBinding, CovidHosViewModel>() {

    companion object {

        private const val TAG = "CovidHosFragment"
    }
    override var getLayoutId: Int = R.layout.fragment_covid_hos
    override var getViewModel: Class<CovidHosViewModel> = CovidHosViewModel::class.java
    val fragmentArray = arrayOf("내위치","전체")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //viewPager & tabLayout 세팅
        viewBinding.apply {
            val viewPager = covdHosViewPager
            val tabLayout = tabLayout
            val hosAdapter = HosViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)

            viewPager.adapter = hosAdapter

            TabLayoutMediator(tabLayout,viewPager) {
                tab,position -> tab.text = fragmentArray[position]
            }.attach()
        }

        checkDatabase()
    }


    //Function : db에 데이터가 있는지 확인
    private fun checkDatabase(){
        //room db에 데이터가 있는지 체크.
        viewModel.getOneData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it.isEmpty()) {
                Log.e(TAG, "checkDatabase: empty...")

                //db에 데이터가 없을 경우 WebServer 에게 API 요청하기...
                viewModel.getHosDataFromWeb().observe(
                    viewLifecycleOwner, androidx.lifecycle.Observer {
                        it.data?.let { data ->
                            //db에 데이터 insert 하는 메서드 호출
                            val itemList = data.body.items.item
                            for (item in itemList) {
                                viewModel.insertHos(item)
                            }
                        }
                    })
            }

        })
    }

}