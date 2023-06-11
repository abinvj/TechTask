package com.example.techtask.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.mvvmkotlinexample.retrofit.RetrofitClient
import com.example.techtask.model.ModelLoginRequest
import com.example.techtask.model.ModelLoginResponse
import com.example.techtask.model.ModelUserListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {

    private val loginResult = MutableLiveData<Boolean>()

    fun loginR(request : ModelLoginRequest) : MutableLiveData<Boolean>{
        val call = RetrofitClient.apiInterface.doLogin(request)
        call.enqueue(object : Callback<ModelLoginResponse>{
            override fun onResponse(
                call: Call<ModelLoginResponse>,
                response: Response<ModelLoginResponse>
            ) {
                if(response.isSuccessful){
                    loginResult.value = true
                } else{
                    loginResult.value = false
                }
            }

            override fun onFailure(call: Call<ModelLoginResponse>, t: Throwable) {
                loginResult.value = false
            }

        })
        return loginResult
    }

    val userList = MutableLiveData<ModelUserListResponse>()


    fun getUserList(country: Int): MutableLiveData<ModelUserListResponse> {

        val call = RetrofitClient.apiInterface.getUserLists(country)

        call.enqueue(object : Callback<ModelUserListResponse>{
            override fun onResponse(
                call: Call<ModelUserListResponse>,
                response: Response<ModelUserListResponse>
            ) {
                if (response.isSuccessful){
                    userList.value = response.body()
                }
            }

            override fun onFailure(call: Call<ModelUserListResponse>, t: Throwable) {
                Log.e("UserListApiError",t.message.toString())
            }

        })

        return userList
    }
}