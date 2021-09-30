package com.example.mysecondapplication.ui.main.hospital.all

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysecondapplication.R
import com.example.mysecondapplication.databinding.FragmentAllHosBinding
import com.example.mysecondapplication.paging.HosPagingAdapter
import com.example.mysecondapplication.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_all_hos.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//전체 진료소 화면
class AllHosFragment : BaseFragment<FragmentAllHosBinding, AllHosViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_all_hos
    override var getViewModel: Class<AllHosViewModel> = AllHosViewModel::class.java
    private lateinit var hosPagingAdapter: HosPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        loadData()

    }

    //Function : RecyclerView 초기화
    private fun setupRecyclerView(){
        hosPagingAdapter = HosPagingAdapter()
        allHos_recyclerView.apply {
            adapter = hosPagingAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
        }
    }

    //Function : 데이터 가져오기
    private fun loadData(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allHosDataFlow.collectLatest { data ->
                hosPagingAdapter.submitData(data)
            }
        }
    }

}