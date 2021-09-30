package com.example.mysecondapplication.di.main.hosp

import com.example.mysecondapplication.data.network.HosApiHelper
import com.example.mysecondapplication.data.repository.CovidHosRepository
import com.example.mysecondapplication.data.repository.MyHosRepository
import com.example.mysecondapplication.data.room.AppDatabase
import com.example.mysecondapplication.di.CustomScope
import dagger.Module
import dagger.Provides

@Module
object CovidHosModule {

    @CustomScope.HosScope
    @JvmStatic
    @Provides
    fun provideCovidHosRepository(database: AppDatabase, api:HosApiHelper) =
        CovidHosRepository(database,api)

    @CustomScope.HosScope
    @JvmStatic
    @Provides
    fun provideMyHosRepository(database: AppDatabase) =
        MyHosRepository(database)
}