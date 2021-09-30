package com.example.mysecondapplication.data.model

import com.google.gson.annotations.SerializedName

data class CovidCountry(
    @SerializedName("NowCase") //국내 격리자수
    val nowCase: String,
    @SerializedName("TodayDeath") //오늘 하루 사망자수
    val todayDeath: String,
    @SerializedName("TodayRecovered") //국내 완치자수
    val todayRecovered: String,
    @SerializedName("TotalCase") //국내 확진자수
    val totalCase: String,
    @SerializedName("TotalCaseBefore") //전날 대비 환자수
    val totalCaseBefore: String,
    @SerializedName("TotalChecking") //총 검사완료 수
    val totalChecking: String,
    @SerializedName("TotalDeath") //국내 사망자수
    val totalDeath: String,
    @SerializedName("TotalRecovered") //국내 완치자수
    val totalRecovered: String,
    @SerializedName("caseCount") //국내 검사결과 양성 (명)
    val caseCount: String,
    @SerializedName("casePercentage") //국내 검사결과 양성 (퍼센트)
    val casePercentage: String,
    @SerializedName("checkingCounter") //국내 검사중(명)
    val checkingCounter: String,
    @SerializedName("checkingPercentage") //국내 검사중(퍼센트)
    val checkingPercentage: String,
    @SerializedName("city1n") //시도별 확진환자 현황1(이름)
    val city1n: String,
    @SerializedName("city1p") //시도별 확진환자 현황1(퍼센트)
    val city1p: String,
    @SerializedName("city2n") //시도별 확진환자 현황2(이름)
    val city2n: String,
    @SerializedName("city2p") //시도별 확진환자 현황2(퍼센트)
    val city2p: String,
    @SerializedName("city3n") //시도별 확진환자 현황3(이름)
    val city3n: String,
    @SerializedName("city3p") //시도별 확진환자 현황3(퍼센트)
    val city3p: String,
    @SerializedName("city4n") //시도별 확진환자 현황4(이름)
    val city4n: String,
    @SerializedName("city4p") //시도별 확진환자 현황4(퍼센트)
    val city4p: String,
    @SerializedName("city5n") //시도별 확진환자 현황5(이름)
    val city5n: String,
    @SerializedName("city5p") //시도별 확진환자 현황5(퍼센트)
    val city5p: String,
    @SerializedName("deathPercentage") //국내 사망률
    val deathPercentage: Double,
    @SerializedName("notcaseCount") //국내 검사결과 음성(명)
    val notcaseCount: String,
    @SerializedName("notcasePercentage") //국내 검사결과 음성(퍼센트)
    val notcasePercentage: String,
    @SerializedName("recoveredPercentage") //국내 완치율(퍼센트)
    val recoveredPercentage: Double,
    @SerializedName("resultCode") //응답코드
    val resultCode: String,
    @SerializedName("resultMessage") //API 처리 결과
    val resultMessage: String,
    @SerializedName("source") //데이터 수집 방법
    val source: String,
    @SerializedName("updateTime") //정보 업데이트 기준
    val updateTime: String
)
