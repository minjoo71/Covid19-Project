package com.example.mysecondapplication.di.app

import com.example.mysecondapplication.di.main.MainActivityBuilderModule
import com.example.mysecondapplication.di.main.MainModule
import com.example.mysecondapplication.di.main.MainViewModelModule
import com.example.mysecondapplication.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * 프로젝트의 액티비티 정의하는 곳*/
@Module
abstract class ActivityBuilderModule {

    //서브컴포넌트로 지정
    @ContributesAndroidInjector(
        //이 서브 컴포넌트 안에서 사용할 모듈들 정의
        modules = [
            MainActivityBuilderModule::class,
            MainModule::class,
            MainViewModelModule::class
        ]
    )
    //MainActivity
    abstract fun contributeMainActivity(): MainActivity
}