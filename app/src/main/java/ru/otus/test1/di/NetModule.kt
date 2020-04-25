package ru.otus.test1.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.otus.test1.net.Api
import javax.inject.Singleton

@Module
class NetModule{
    private val  BASE_POINT = "https://picsum.photos/"
    @Provides
    @Singleton
    fun provideService() =
         Retrofit.Builder()
            .baseUrl(BASE_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            // .client(okHttpClient)
            .build().create(Api::class.java)

}