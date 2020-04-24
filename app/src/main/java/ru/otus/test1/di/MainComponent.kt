package ru.otus.test1.di

import dagger.Component
import ru.otus.test1.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppComponent::class]
    ,modules = [NetModule::class, AdapterModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}