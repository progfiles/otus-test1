package ru.otus.test1

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.otus.test1.entity.ImageData

interface Api {
    @GET("v2/list")
    suspend fun getImages(): Response<List<ImageData>>

    @GET("id/{id}/info")
    suspend fun getImageDetail(@Path("id") id: Int): Response<ImageData>

}
