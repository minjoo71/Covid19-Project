package com.example.mysecondapplication.ui.main.hospital.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mysecondapplication.data.model.covidHos.Item
import com.example.mysecondapplication.data.repository.MyHosRepository
import javax.inject.Inject

class MyHosViewModel @Inject constructor(private val repository: MyHosRepository) : ViewModel() {

    fun getMyHosData(gungu : String) : LiveData<List<Item>> {

        val getHosData: LiveData<List<Item>>
        getHosData = repository.getMyHosFromRoom(gungu)

        return getHosData
    }

}