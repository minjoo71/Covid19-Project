package com.example.mysecondapplication.di.main

import com.example.mysecondapplication.di.CustomScope
import com.example.mysecondapplication.di.main.home.CovidMainModule
import com.example.mysecondapplication.di.main.hosp.CovidHosModule
import com.example.mysecondapplication.ui.main.hospital.CovidHosFragment
import com.example.mysecondapplication.ui.main.home.CovidMainFragment
import com.example.mysecondapplication.ui.main.hospital.all.AllHosFragment
import com.example.mysecondapplication.ui.main.hospital.my.MyHosFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * 프래그먼트들은 메인액티비티컴포넌트 안에서 존재함
 * MainActivityComponent의 SubComponent*/
@Module
abstract class MainActivityBuilderModule {

    @CustomScope.MainScope
    @ContributesAndroidInjector(modules = [CovidMainModule::class])
    abstract fun contributeCovidMainFragment() : CovidMainFragment //메인화면

    @CustomScope.HosScope
    @ContributesAndroidInjector(modules = [CovidHosModule::class])
    abstract fun contributeCovidHosFragment() : CovidHosFragment //진료소 화면

    @ContributesAndroidInjector
    abstract fun contributeAllHosFragment() : AllHosFragment //전체 진료소

    @ContributesAndroidInjector
    abstract fun contributeMyHosFragment() : MyHosFragment //내 진료소




}