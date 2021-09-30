package com.example.mysecondapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mysecondapplication.data.model.covidHos.Item

/**
 * 데이터베이스의 전체적인 소유자 역할을 하며 DB를 새롭게 생성하거나 버전을 관리한다.
 * 데이터베이스 이용을 위한 DAO 객체 획득 함수를 제공하는 클래스다.*/
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    //다오 객체 획득
    abstract fun getHosDao(): HosDao

}