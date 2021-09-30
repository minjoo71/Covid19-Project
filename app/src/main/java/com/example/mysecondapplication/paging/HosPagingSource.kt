package com.example.mysecondapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mysecondapplication.data.model.covidHos.Item
import com.example.mysecondapplication.data.room.HosDao
/**
 * 로컬 데이터베이스 또는 네트워크로 데이터를 불러오는 것을 담당
 * 데이터 소스를 정의하고 데이터를 가져오는 방법을 정의한다.*/
class HosPagingSource(private val hosDao: HosDao) : PagingSource<Int, Item>() {

    private companion object{
        const val INITIAL_PAGE_INDEX = 1
    }

    //스와이프 Refresh, 데이터 업데이트 등으로 현재 목록을 대체할 새 데이터를 로드할 때 사용
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return null
    }

    //사용자가 스크롤 할 때마다 데이터를 비동기적으로 가져오기 위해 load() 호출
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {

        //params.key에 현재 페이지 인덱스를 관리
        //load()를 처음 호출하는 경우, key 는 null 이므로 초기 페이지를 정의해야 함
        val position = params.key ?: INITIAL_PAGE_INDEX

        //params.loadSize는 가져올 데이터의 갯수를 관리
        //전체 선별진료소 목록 가져옴
        val response = hosDao.getAllData(params.loadSize)

        //load 성공시
       return LoadResult.Page(
            data = response, //데이터
            prevKey = if(position == 1) null else -1, //이전 페이지
            nextKey = position.plus(1) //다음 페이지
        )
    }
}