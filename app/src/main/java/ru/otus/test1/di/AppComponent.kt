package ru.otus.test1.di

import android.content.Context
import com.voltmobi.core_api.AppProvider
import dagger.BindsInstance
import dagger.Component

@Component
interface AppComponent : AppProvider {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Context): Builder
        fun build(): AppComponent
    }
}