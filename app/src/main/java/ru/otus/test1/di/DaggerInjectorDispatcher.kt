package ru.otus.test1.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import ru.otus.test1.App
import ru.otus.test1.MainActivity

class DaggerInjectorDispatcher: Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        when(activity){
            is MainActivity ->{
                DaggerMainComponent
                    .builder()
                    .appComponent(App.appComponent)
                    .build()
                    .inject(activity)
            }
        }
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }
}