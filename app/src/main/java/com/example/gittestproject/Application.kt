package com.example.gittestproject

import android.app.Application
import android.content.Context
import com.example.gittestproject.di.ApplicationComponent
import com.example.gittestproject.di.ApplicationModule
import com.example.gittestproject.di.DaggerApplicationComponent
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class Application : Application() {

    companion object {
        lateinit var component: ApplicationComponent
            private set

        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        context = this
        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        super.onCreate()
    }

}