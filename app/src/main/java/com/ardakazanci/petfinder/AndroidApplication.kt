package com.ardakazanci.petfinder

import android.app.Application
import com.ardakazanci.petfinder.core.di.ApplicationComponent
import com.ardakazanci.petfinder.core.di.ApplicationModule
import com.ardakazanci.petfinder.core.di.DaggerApplicationComponent

class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}