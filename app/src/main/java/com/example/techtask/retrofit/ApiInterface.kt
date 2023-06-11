package com.example.mvvmkotlinexample.retrofit
import com.example.techtask.model.ModelLoginRequest
import com.example.techtask.model.ModelLoginResponse
import com.example.techtask.model.ModelUserListResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST("login")
    fun doLogin(@Body request : ModelLoginRequest) : Call<ModelLoginResponse>

    //https://reqres.in/api/users?page=2
    @GET("users")
    fun getUserLists(@Query("page") page : Int) : Call<ModelUserListResponse>

}