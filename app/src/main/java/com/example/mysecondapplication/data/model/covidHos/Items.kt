package com.example.mysecondapplication.data.model.covidHos

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class Items(
    @Element(name="item")
    val item: List<Item>
)
