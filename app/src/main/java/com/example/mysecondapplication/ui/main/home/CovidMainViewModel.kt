package com.example.mysecondapplication.ui.main.home

import androidx.lifecycle.ViewModel
import com.example.mysecondapplication.data.repository.CovidMainRepository
import javax.inject.Inject

/**
 * CovidMainFragment에게 데이터를 전달하고, Repository와 상호작용
 * 생성자 주입을 통해 viewmodel을 injecting*/
class CovidMainViewModel @Inject constructor(
    //repository 를 주입한다.
    private val repository: CovidMainRepository
) : ViewModel() {

    //Function : repository 에 데이터 요청한다.
    //요청 결과로 liveData 인스턴스를 return 받는다.
    fun getCovidData() = repository.getCovidData()

    //Function : viewModel destroy.
    override fun onCleared() {
        //dispose() 호출
        repository.disposeComposite()
        super.onCleared()
    }

}