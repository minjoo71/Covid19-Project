package com.example.mysecondapplication.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysecondapplication.R
import com.example.mysecondapplication.adapter.CovidDataAdapter
import com.example.mysecondapplication.data.Status
import com.example.mysecondapplication.data.model.covidMain.CovidStatus
import com.example.mysecondapplication.databinding.FragmentCovidMainBinding
import com.example.mysecondapplication.ui.base.BaseFragment
import com.example.mysecondapplication.util.hide
import com.example.mysecondapplication.util.show
import kotlinx.android.synthetic.main.fragment_covid_main.*
import java.util.*

/**
 * 메인 화면 - 코로나 상황판*/
class CovidMainFragment : BaseFragment<FragmentCovidMainBinding, CovidMainViewModel>() {

    private val TAG = "CovidMainFragment"

    override var getLayoutId: Int = R.layout.fragment_covid_main // 현재 레이아웃 지정
    override var getViewModel: Class<CovidMainViewModel> = CovidMainViewModel::class.java // 뷰모델 지정

    //리사이클러뷰 어댑터
    private var covidDataAdapter = CovidDataAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewInit()
        getCovidData()

    }

    //Function : RecyclerView initiation
    private fun recyclerViewInit(){
        main_recyclerview.let {
            it.layoutManager = GridLayoutManager(requireContext(),2,LinearLayoutManager.VERTICAL,false)
            it.setHasFixedSize(true)
            it.adapter = covidDataAdapter
        }
    }

    //Function : 코로나 국내 현황 데이터를 가져와 UI에 뿌림
    private fun getCovidData() {
        //프로그레스 바 show
        home_progressbar.show()

        //이전 옵저버 제거하기
        viewModel.getCovidData().removeObservers(viewLifecycleOwner)
        //viewModel의 LiveData Observe 하기
        viewModel.getCovidData()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                it?.let { status ->
                    when (status.status) {
                        Status.StatusType.SUCCESS -> {

                            //데이터 가져오기
                            it.data?.let { data ->
                                home_progressbar.hide()
                                Log.e(TAG, "getCovidCountry: data 값 => "+ data)

                                //전체 현황을 view에 Binding 하기
                                viewBinding.covidData = data

                                //아이템 리스트에 데이터 담기
                                val covidList: List<CovidStatus> = listOf(
                                    data.seoul,
                                    data.busan,
                                    data.incheon,
                                    data.gwangju,
                                    data.jeonbuk,
                                    data.chungbuk,
                                    data.jeonnam,
                                    data.gyeongbuk,
                                    data.daegu,
                                    data.ulsan,
                                    data.daejeon,
                                    data.sejong,
                                    data.chungnam,
                                    data.gyeonggi,
                                    data.gyeongnam,
                                    data.gangwon,
                                    data.jeju,
                                    data.quarantine
                                )

                                //데이터를 리사이클러뷰 어댑터에 set 하기
                                covidDataAdapter.setList(covidList)

                            }
                        }
                        Status.StatusType.ERROR -> {
                            Status.error("Error : ${it.message}", null)
                        }
                    }
                }
            })
    }

}
