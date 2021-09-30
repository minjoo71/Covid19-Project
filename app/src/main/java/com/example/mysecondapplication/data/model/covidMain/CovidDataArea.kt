package com.example.mysecondapplication.data.model.covidMain

import com.google.gson.annotations.SerializedName

data class CovidDataArea(
    @SerializedName("korea")
    var korea: CovidStatus,

    @SerializedName("seoul")
    var seoul: CovidStatus,

    @SerializedName("busan")
    var busan: CovidStatus,

    @SerializedName("daegu")
    var daegu: CovidStatus,

    @SerializedName("incheon")
    var incheon: CovidStatus,

    @SerializedName("gwangju")
    var gwangju: CovidStatus,

    @SerializedName("daejeon")
    var daejeon: CovidStatus,

    @SerializedName("ulsan")
    var ulsan: CovidStatus,

    @SerializedName("sejong")
    var sejong: CovidStatus,

    @SerializedName("gyeonggi")
    var gyeonggi: CovidStatus,

    @SerializedName("gangwon")
    var gangwon: CovidStatus,

    @SerializedName("chungbuk")
    var chungbuk: CovidStatus,

    @SerializedName("chungnam")
    var chungnam: CovidStatus,

    @SerializedName("jeonbuk")
    var jeonbuk: CovidStatus,

    @SerializedName("jeonnam")
    var jeonnam: CovidStatus,

    @SerializedName("gyeongbuk")
    var gyeongbuk: CovidStatus,

    @SerializedName("gyeongnam")
    var gyeongnam: CovidStatus,

    @SerializedName("jeju")
    var jeju: CovidStatus,

    @SerializedName("quarantine")
    var quarantine: CovidStatus
)
