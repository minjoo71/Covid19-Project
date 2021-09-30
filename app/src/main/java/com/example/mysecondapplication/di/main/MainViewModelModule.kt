package com.example.mysecondapplication.di.main

import androidx.lifecycle.ViewModel
import com.example.mysecondapplication.di.viewmodel.ViewModelKey
import com.example.mysecondapplication.ui.main.home.CovidMainViewModel
import com.example.mysecondapplication.ui.main.hospital.all.AllHosViewModel
import com.example.mysecondapplication.ui.main.hospital.CovidHosViewModel
import com.example.mysecondapplication.ui.main.hospital.my.MyHosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * 각각의 뷰모델들을 의존성으로 추가*/
@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CovidMainViewModel::class)
    abstract fun bindCovidMainViewModel(covidMainViewModel: CovidMainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CovidHosViewModel::class)
    abstract fun bindCovidHosViewModel(covidHosViewModel: CovidHosViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyHosViewModel::class)
    abstract fun bindMyHosViewModel(myHosViewModel: MyHosViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AllHosViewModel::class)
    abstract fun bindAllHosViewModel(allHosViewModel: AllHosViewModel): ViewModel
}