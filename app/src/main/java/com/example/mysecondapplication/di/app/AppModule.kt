package com.example.mysecondapplication.di.app

import android.content.Context
import androidx.room.Room
import com.example.mysecondapplication.data.network.ApiHelper
import com.example.mysecondapplication.data.network.HosApiHelper
import com.example.mysecondapplication.data.room.AppDatabase
import com.example.mysecondapplication.util.COVID_COUNTRY_URL
import com.example.mysecondapplication.util.COVID_HOSPITAL_URL
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * AppComponent의 모듈
 * application level 의 dependency(인스턴스)*/
@Module
object AppModule {
    /* --- API --- */
    //provide retrofit instance to the entire application
    //api 인스턴스를 어디에서건 injecting 할 수 있음
    @Singleton
    @JvmStatic
    @Provides
    fun provideApiHelper(): ApiHelper = ApiHelper.create(COVID_COUNTRY_URL)


    @Singleton
    @JvmStatic
    @Provides
    fun provideHosApiHelper(): HosApiHelper = HosApiHelper.create(COVID_HOSPITAL_URL)


    /* --- ROOM --- */
    //provide database instance to the entire application
    //database instance 를 어디에서건 injecting 할 수 있음
    @Singleton
    @JvmStatic
    @Provides
    fun provideAppDatabase(context: Context) : AppDatabase {
        return synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()
        }
    }
}