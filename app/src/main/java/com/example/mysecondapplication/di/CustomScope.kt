package com.example.mysecondapplication.di

import javax.inject.Scope

class CustomScope {

    @Scope
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class MainScope

    @Scope
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class HosScope

}