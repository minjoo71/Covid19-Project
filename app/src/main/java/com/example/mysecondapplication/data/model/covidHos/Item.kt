package com.example.mysecondapplication.data.model.covidHos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
@Entity(tableName = "hosp_table")
data class Item(

    @PropertyElement(name="sgguNm")
    val sgguNm: String, //구

    @PropertyElement(name="sidoNm")
    val sidoNm: String, //시도

    @PropertyElement(name="telno")
    val telno: String, //병원번호

    @PropertyElement(name="yadmNm")
    @PrimaryKey
    val yadmNm: String //병원이름
)