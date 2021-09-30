package com.example.mysecondapplication.di.app

import android.app.Application
import android.content.Context
import com.example.mysecondapplication.di.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

//App 컴포넌트
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, AppModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {

    //컴포넌트 생성하기 위한 Builder용 어노테이션
    @Component.Builder
    interface Builder {

        //application 주입 -> dagger를 통해 application를 자유롭게 사용
        @BindsInstance
        fun application(application: Application): Builder

        //context 주입 -> dagger를 통해 context를 자유롭게 사용
        @BindsInstance
        fun context(context: Context): Builder

        //AppComponent 반환
        fun build(): AppComponent
    }
}