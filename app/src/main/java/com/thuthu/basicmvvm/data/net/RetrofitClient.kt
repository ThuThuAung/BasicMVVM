package com.thuthu.basicmvvm.data.net

import com.thuthu.basicmvvm.data.model.UserResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val userApi: UserApi

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    }

    init {
        val builder = OkHttpClient.Builder()
        val okHttpClient = builder.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        userApi = retrofit.create(UserApi::class.java)
    }

    fun loadData(): Call<List<UserResponse>> {
        return userApi.getData()
    }

}