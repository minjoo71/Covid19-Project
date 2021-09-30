package com.example.mysecondapplication.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * viewModelProviderFactory 인스턴스를 ViewModel 이 사용되는 Activity에 주입하기 위한 모듈*/
@Module
abstract class ViewModelProviderFactoryModule {

    //이미 생성한 ViewModelPrivderFactory를 파라미터로 전달한다.
    @Binds
    abstract fun bindViewModelProvider(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}