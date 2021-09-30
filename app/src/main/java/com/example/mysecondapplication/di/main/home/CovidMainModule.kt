package com.example.mysecondapplication.di.main.home

import com.example.mysecondapplication.data.network.ApiHelper
import com.example.mysecondapplication.data.repository.CovidMainRepository
import com.example.mysecondapplication.di.CustomScope
import dagger.Module
import dagger.Provides


@Module
object CovidMainModule {

    //CovidMainRepository 를 의존성으로 제공한다.
    @CustomScope.MainScope
    @JvmStatic
    @Provides
    fun provideCovidMainRepository(api : ApiHelper) = CovidMainRepository(api)
}