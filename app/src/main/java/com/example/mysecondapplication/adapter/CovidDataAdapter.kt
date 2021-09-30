package com.example.mysecondapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mysecondapplication.R
import com.example.mysecondapplication.data.model.covidMain.CovidStatus
import com.example.mysecondapplication.databinding.ItemCityStatusBinding

class CovidDataAdapter : RecyclerView.Adapter<CovidDataAdapter.ViewHolder>() {

    //아이템(데이터)을 담을 리스트
    private var covidDataList: List<CovidStatus> = listOf()

    //뷰홀더 클래스
    //DataBinding 해야 할 객체는 ItemCityStatusBinding의 root(View)를 RecyclerView.ViewHolder에 넘겨준다.
    inner class ViewHolder(val binding: ItemCityStatusBinding) : RecyclerView.ViewHolder(binding.root)

    //뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidDataAdapter.ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_city_status,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CovidDataAdapter.ViewHolder, position: Int) {

        val data = covidDataList[position]

        //뷰에 데이터 바인딩 하기
        holder.binding.cityData = data
    }

    override fun getItemCount(): Int = covidDataList.size

    //adapter의 데이터를 갱신하는 function
    fun setList(covidDataList: List<CovidStatus>) {
        this.covidDataList = covidDataList
        notifyDataSetChanged()
    }
}