package com.example.mysecondapplication.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysecondapplication.data.Status
import com.example.mysecondapplication.data.model.covidHos.Item
import com.example.mysecondapplication.data.model.covidHos.Response
import com.example.mysecondapplication.data.network.HosApiHelper
import com.example.mysecondapplication.data.room.AppDatabase
import com.example.mysecondapplication.data.room.HosDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * web server 에 api 요청을 하고, room 의 dao 에 접근함
 * api 인스턴스, database 인스턴스 injecting*/
class CovidHosRepository  @Inject constructor(
        private val hosDatabase: AppDatabase,
        private val api:HosApiHelper) {

    private val TAG = "CovidHosRepository"

    private val hosDao: HosDao
    val getOneData: LiveData<List<Item>>

    //observable을 모두 해제
    private val compositeDisposable = CompositeDisposable()

    init {
        hosDao = hosDatabase.getHosDao()
        getOneData = hosDao.getOneData()
        Log.e(TAG, "getOneData() 실행...")
    }

    //Function : 선별진료소 데이터를 db에 insert 한다.
    suspend fun insertHos(item: Item) {
        hosDao.insertHosData(item)
    }


    //Function : web server에 데이터 요청한다.
    fun getHosDataFromWeb() : LiveData<Status<Response>>{

        //Item 타입의 데이터를 관찰하는 liveData 인스턴스를 만든다.
        val liveData = MutableLiveData<Status<Response>>()

        //request api
        compositeDisposable.add(
            api.getHosData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        it.let {
                            //liveData 에 결과값을 등록한다.
                            liveData.postValue(Status.success(it))
                            return@subscribeBy
                        }
                        liveData.postValue(Status.error("Error", null))
                    },
                    onError = {
                        liveData.postValue(Status.error("Error : ${it.message}", null))
                    }
                )
        )
        return liveData
    }



    //Function : dispose repository composite
    fun disposeComposite(){
        compositeDisposable.dispose()
    }
}