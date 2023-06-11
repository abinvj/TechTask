package com.example.techtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techtask.model.ModelLoginRequest
import com.example.techtask.model.ModelUserListResponse
import com.example.techtask.repository.Repository

class UserListViewModel : ViewModel() {
    lateinit var userListLiveData: MutableLiveData<ModelUserListResponse>
    lateinit var userLogin: MutableLiveData<Boolean>

    fun getLogin(request:ModelLoginRequest) : LiveData<Boolean> {
        userLogin = Repository.loginR(request)
        return userLogin
    }
    fun getUser(count : Int) : LiveData<ModelUserListResponse>? {
        userListLiveData = Repository.getUserList(count)
        return userListLiveData
    }

}