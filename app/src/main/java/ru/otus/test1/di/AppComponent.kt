package ru.otus.test1.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component
interface AppComponent {

    fun provideContext():Context

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Context):Builder
        fun build():AppComponent
    }
}