package com.ardakazanci.petfinder.core.di

import com.ardakazanci.petfinder.AndroidApplication
import com.ardakazanci.petfinder.core.di.vm.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
}