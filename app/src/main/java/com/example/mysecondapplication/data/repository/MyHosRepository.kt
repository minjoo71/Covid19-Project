package com.example.mysecondapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.mysecondapplication.data.model.covidHos.Item
import com.example.mysecondapplication.data.room.AppDatabase
import com.example.mysecondapplication.data.room.HosDao
import javax.inject.Inject

class MyHosRepository @Inject constructor(private val hosDatabase: AppDatabase) {

    companion object{
        private const val TAG = "MyHosRepository"
    }

    //dao 객체
    private val hosDao: HosDao = hosDatabase.getHosDao()

    //내위치 진료소 가져오기
    fun getMyHosFromRoom(gungu : String) :  LiveData<List<Item>>{

        val getMyhos : LiveData<List<Item>>
        getMyhos =  hosDao.getMyHosData(gungu)

        return getMyhos
    }

}