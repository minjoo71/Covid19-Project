package com.example.mysecondapplication.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mysecondapplication.R
import com.example.mysecondapplication.data.model.covidHos.Item
import com.example.mysecondapplication.databinding.ItemMyHosBinding
/**
 * PagingData를 RecyclerView에 바인딩 하기 위해 사용*/
class HosPagingAdapter : PagingDataAdapter<Item, HosPagingAdapter.HosViewHolder>(diffCallback) {

    //뷰홀더 클래스
    inner class HosViewHolder(val binding: ItemMyHosBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.yadmNm == newItem.yadmNm
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HosPagingAdapter.HosViewHolder =
        HosViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_my_hos,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: HosPagingAdapter.HosViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.myHosData = data
    }


}