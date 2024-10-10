package com.sunil.albertsonsusers.states

import com.sunil.albertsonsusers.model.Result
import com.sunil.albertsonsusers.model.userResultModel

sealed class UserStates  {

    object initial : UserStates()
    object loading : UserStates()
    data class Sucess(val response : List<Result>) : UserStates()
    data class Error(val error : String) : UserStates()
}