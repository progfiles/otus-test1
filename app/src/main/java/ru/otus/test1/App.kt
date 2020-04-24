package ru.otus.test1

import android.app.Application
import ru.otus.test1.di.AppComponent
import ru.otus.test1.di.DaggerAppComponent
import ru.otus.test1.di.DaggerInjectorDispatcher

class App: Application() {



    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        registerActivityLifecycleCallbacks(DaggerInjectorDispatcher())

    }

    companion object{
        lateinit var appComponent: AppComponent
    }
}