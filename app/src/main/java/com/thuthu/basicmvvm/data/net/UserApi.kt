package com.thuthu.basicmvvm.data.net

import com.thuthu.basicmvvm.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET


interface UserApi {

    @GET("/users")
    fun getData(): Call<List<UserResponse>>


}