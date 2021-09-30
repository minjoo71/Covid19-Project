package com.example.mysecondapplication.data.network

import com.example.mysecondapplication.data.model.covidMain.CovidDataArea
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHelper {
    companion object {

        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        //Function : for configure retrofit and return ApiHelper
        fun create(baseUrl : String) : ApiHelper {
            val okHttp = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            //retrofit 인스턴스
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttp)
                .build()
                .create(ApiHelper::class.java)
        }
    }

    //Covid 현황 데이터
    @GET("/korea/country/new/")
    fun getCovidData(
        @Query("serviceKey") serviceKey: String
    ): Observable<CovidDataArea>



}