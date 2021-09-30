package com.example.mysecondapplication.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mysecondapplication.di.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T: ViewDataBinding, VM: ViewModel> : DaggerFragment() {

    //Injecting ViewModelProviderFactory
    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    lateinit var viewBinding: T
    lateinit var viewModel: VM

    abstract var getLayoutId: Int //레이아웃
    abstract var getViewModel: Class<VM> //뷰모델

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //데이터 바인딩 -> 레이아웃 XML 지정하고 초기화, 해당 XML을 이용하기 위한 바인딩 클래스의 객체를 반환
        viewBinding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)

        //뷰모델 생성
        viewModel = ViewModelProvider(this, viewModelFactory)[getViewModel]

        return viewBinding.root
    }
}