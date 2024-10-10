package com.sunil.albertsonsusers.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunil.albertsonsusers.data.ApiRepository
import com.sunil.albertsonsusers.model.userResultModel
import com.sunil.albertsonsusers.states.UserStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    private val _userState = MutableStateFlow<UserStates>(UserStates.initial)
    val userState : StateFlow<UserStates> get() = _userState

    private val _userData = MutableLiveData<userResultModel>()
    val userData : LiveData<userResultModel> get() = _userData

    fun fetchUsers(input : Int?){
        viewModelScope.launch {

            _userState.value = UserStates.initial
            try {
                val users = apiRepository.getUsers(input)
                val result = users.results
                _userState.value = UserStates.Sucess(result)

                _userData.value = users


            }catch (e : Exception){
                _userState.value =  UserStates.Error(e.message.toString() ?: "Something went Wrong")
            }

        }
    }

}