package com.example.mysecondapplication.ui.main.hospital.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mysecondapplication.data.room.AppDatabase
import com.example.mysecondapplication.data.room.HosDao
import com.example.mysecondapplication.paging.HosPagingSource
import javax.inject.Inject

class AllHosViewModel @Inject constructor( private val hosDatabase: AppDatabase) : ViewModel() {

    //dao 객체 획득
    private val hosDao: HosDao = hosDatabase.getHosDao()

    //size 매개변수를 PagingConfig 를 통해 설정함
    //pager는 PagingData 인스턴스를 구성하는 반응형 스트림을 생성한다.
    val allHosDataFlow = Pager(PagingConfig(10)) { //pageSize는 각 페이지에 로드할 데이터 수를 가리킨다.
        //데이터 가져오기
        HosPagingSource(hosDao)
    }.flow.cachedIn(viewModelScope)
}