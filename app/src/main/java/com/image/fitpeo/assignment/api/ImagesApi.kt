package com.image.fitpeo.assignment.api

import com.image.fitpeo.assignment.model.ImagesModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ImagesApi {

    @GET("photos")
    fun getImagesList() : retrofit2.Call<List<ImagesModel>>

    companion object {
            private var imagesApi: ImagesApi? = null

            fun getInstance() : ImagesApi {
                if (imagesApi == null) {
                    val retrofit = Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    imagesApi = retrofit.create(ImagesApi::class.java)
                }
                return imagesApi!!
            }
    }
}