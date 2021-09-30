package com.example.mysecondapplication.data.network

import com.example.mysecondapplication.data.model.covidHos.Response
import com.example.mysecondapplication.util.HOSPITAL_SERVICE_KEY
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET

interface HosApiHelper {
    companion object {

        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        //Function : for configure retrofit and return ApiHelper
        fun create(baseUrl : String) : HosApiHelper{
            val okHttp = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            //retrofit 인스턴스
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttp)
                .build()
                .create(HosApiHelper::class.java)
        }
    }

    @GET("B551182/pubReliefHospService/getpubReliefHospList"+
            "?serviceKey=${HOSPITAL_SERVICE_KEY}"+
            "&pageNo=1"+
            "&numOfRows=661"+
            "&spclAdamTyCd=99")
    fun getHosData(): Observable<Response>
}