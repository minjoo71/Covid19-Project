package com.example.mysecondapplication.data.model.covidMain

import com.google.gson.annotations.SerializedName

data class CovidStatus(
    @SerializedName("countryName")
    var countryName: String,

    @SerializedName("newCase")
    var newCase: String,

    @SerializedName("totalCase")
    var totalCase: String,

    @SerializedName("recovered")
    var recovered: String,

    @SerializedName("death")
    var death: String,

    @SerializedName("percentage")
    var percentage: String,

    @SerializedName("newFcase")
    var newFCase: String,

    @SerializedName("newCcase")
    var newCCase: String
)
