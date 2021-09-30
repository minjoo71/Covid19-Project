package com.example.mysecondapplication.ui.main.hospital

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysecondapplication.data.model.covidHos.Item
import com.example.mysecondapplication.data.repository.CovidHosRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * repository 주입 받기.
 * */
class CovidHosViewModel @Inject constructor(
        private val repository: CovidHosRepository
) : ViewModel() {

    val getOneData: LiveData<List<Item>> = repository.getOneData

    //Function : room db에 데이터 insert 하는 메서드
    fun insertHos(item: Item) {
        viewModelScope.launch {
            //repository 에서 호출하기
            repository.insertHos(item)
        }
    }

    //Function : web server 에 직접 api 요청하는 메서드
    fun getHosDataFromWeb() = repository.getHosDataFromWeb()

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}