package com.example.mysecondapplication.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysecondapplication.data.model.covidHos.Item

/**
 * Data Access Object
 * 실제 데이터베이스에 접근할 수 있는 메서드를 정의해놓은 인터페이스다.
 * DAO의 함수가 호출되면 데이터베이스 접근이 발생하는데, 이는 메인 스레드에서는 허용하지 않는다.
 * */
@Dao
interface HosDao {
    //DAO에서 suspend function을 선언하면 coroutine을 이용하여 background에서 동작
    //따라서 DB를 접근할 때 따로 background thread를 만들어서 접근할 필요가 없음

    //데이터 저장
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHosData(item : Item)

    //데이터 삭제
    @Delete
    suspend fun deleteHosData(item: Item)

    //db에 데이터가 있는지 확인
    @Query("SELECT * FROM hosp_table LIMIT 1")
    fun getOneData() : LiveData<List<Item>>

    //모든 데이터(진료소) 가져오기
    @Query("SELECT * FROM hosp_table LIMIT :size")
    suspend fun getAllData(size: Int) : List<Item>

    //현재 위치('구')를 기준으로 데이터(진료소)가져오기
    @Query("SELECT * FROM hosp_table WHERE sgguNm =:gungu")
    fun getMyHosData(gungu : String) : LiveData<List<Item>>

}