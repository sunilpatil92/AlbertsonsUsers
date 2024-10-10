package com.sunil.albertsonsusers.retrofit

import com.sunil.albertsonsusers.model.userResultModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService  {

    @GET("api/")
    suspend fun getUsers(@Query("results") input: Int?): userResultModel

}