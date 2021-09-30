package com.example.mysecondapplication.data.model.covidHos

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name="body")
data class Body(
    @Element
    val items: Items,
    @PropertyElement
    val numOfRows: String,
    @PropertyElement
    val pageNo: String,
    @PropertyElement
    val totalCount: String
)