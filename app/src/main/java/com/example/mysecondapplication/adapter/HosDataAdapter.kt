package com.example.mysecondapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mysecondapplication.R
import com.example.mysecondapplication.data.model.covidHos.Item
import com.example.mysecondapplication.databinding.ItemMyHosBinding

class HosDataAdapter : RecyclerView.Adapter<HosDataAdapter.ViewHolder>() {

    //아이템(데이터)을 담을 리스트
    private var hosDataList: List<Item> = listOf()

    //뷰홀더 클래스
    inner class ViewHolder(val binding: ItemMyHosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HosDataAdapter.ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_my_hos,
                parent,
                false)
        )

    override fun onBindViewHolder(holder: HosDataAdapter.ViewHolder, position: Int) {

        val data = hosDataList[position]
        holder.binding.myHosData = data

    }

    override fun getItemCount(): Int = hosDataList.size

    fun setList(list: List<Item>) {
        this.hosDataList = list
        notifyDataSetChanged()
    }

}