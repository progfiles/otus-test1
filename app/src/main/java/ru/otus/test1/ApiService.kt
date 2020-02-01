package ru.otus.test1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService{
    private const val BASE_POINT = "https://picsum.photos/"

    fun makeRetrofitService(): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_POINT)
            .addConverterFactory(GsonConverterFactory.create())
           // .client(okHttpClient)
            .build().create(Api::class.java)
    }

}