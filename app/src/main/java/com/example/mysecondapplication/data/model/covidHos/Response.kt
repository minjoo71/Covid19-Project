package com.example.mysecondapplication.data.model.covidHos

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name="response")
data class Response(
    @Element
    val body: Body
)