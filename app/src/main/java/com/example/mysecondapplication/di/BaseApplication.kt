package com.example.mysecondapplication.di


import com.example.mysecondapplication.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * AppComponent를 이용하여 Injector 생성*/
class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder()
                    .application(this)
                    .context(this)
                    .build()
}