package com.sunil.albertsonsusers.data

import com.sunil.albertsonsusers.model.userResultModel
import com.sunil.albertsonsusers.retrofit.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUsers(input: Int?): userResultModel{
        return apiService.getUsers(input)
    }
}