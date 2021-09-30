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

    //데이터 저장
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHosData(item : Item)

    //데이터 삭제
    @Delete
    suspend fun deleteHosData(item: Item)

    //데이터 하나만 가져오기
    //one-shot read 쿼리 즉, 데이터베이스에서 데이터를 한번만 읽고, 그때 결과 반환함...
    //굳이 LiveData 를 사용할 필요가 있나? -> 단순히 suspend 사용해서 비동기식으로 db에 접근하면 될 일?
    @Query("SELECT * FROM hosp_table LIMIT 1")
    fun getOneData() : LiveData<List<Item>>

    //모든 데이터 가져오기
    @Query("SELECT * FROM hosp_table LIMIT :size")
    suspend fun getAllData(size: Int) : List<Item>

    @Query("SELECT * FROM hosp_table WHERE sgguNm =:gungu")
    fun getMyHosData(gungu : String) : LiveData<List<Item>>

}