package com.example.mysecondapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysecondapplication.data.Status
import com.example.mysecondapplication.data.model.covidMain.CovidDataArea
import com.example.mysecondapplication.data.network.ApiHelper
import com.example.mysecondapplication.util.COVID_SERVICE_KEY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//CovidMainRepository 에서 api 인스턴스가 필요하므로... 생성자 주입을 통해 api 인스턴스를 injecting 함
class CovidMainRepository @Inject constructor(private val api: ApiHelper) {

    private val compositeDisposable = CompositeDisposable()


    //Function : web server에 데이터 요청을 하는 function
    fun getCovidData() : LiveData<Status<CovidDataArea>> {

        //liveData 는 주어진 라이프사이클 내에서 관찰 할 수 있는 data holder 역할을 하는데
        //liveData 에 데이터를 set 할 수 있는 것은 이를 구현한 클래스에서 setValue/postValue 를 통해서만 가능하다.
        val liveData = MutableLiveData<Status<CovidDataArea>>()

        //request api
        compositeDisposable.add( //retrofit 이용해 통신 작업을 수행한 후 이를 한꺼번에 dispose 하기 위해서 사용한다.
            api.getCovidData(COVID_SERVICE_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy( //스트림 생성 하고 아이템 발행
                    onNext = {
                        it?.let {
                            //liveData 에 결과 값 - 데이터를 set 한다.
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

    //Function : for dispose repository composite
    fun disposeComposite(){
        //메모리 누수 방지를 위해 명시적인 폐기(dispose)가 필요하다.
        //disposable.dispose() 메서드를 호출하면 언제든지 아이템 발행을 중단할 수 있다.
        compositeDisposable.dispose()
    }
}