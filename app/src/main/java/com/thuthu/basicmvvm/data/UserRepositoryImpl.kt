package com.thuthu.basicmvvm.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import  com.thuthu.basicmvvm.data.db.UserDao
import  com.thuthu.basicmvvm.data.model.User
import  com.thuthu.basicmvvm.data.model.UserResponse
import com.thuthu.basicmvvm.data.net.RetrofitClient
import  com.thuthu.basicmvvm.db
//import kotlin.concurrent.thread

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl : UserRepository {

    private val userDao: UserDao = db.userDao()
    private val retrofitClient = RetrofitClient()
    private var userData: LiveData<User>

    init {
        userData = userDao.getAll()
    }


    override fun getUserData() = userData

    override fun saveUser(user: User) {
        System.out.println("Working or not" + user.email)
        userDao.insert(user)

    }

    override fun searchUsers(query: User): LiveData<User> {

        try {
            if (query != null) {
                userData = userDao.getUser(query.email, query.password)
            }
        } catch (e: Exception) {
            System.out.println("Error" + e)
        }
        return userData
    }


    override fun loadList(): LiveData<List<UserResponse>> {
        val data = MutableLiveData<List<UserResponse>>()

        retrofitClient.loadData().enqueue(object : Callback<List<UserResponse>> {
            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                data.value = null
                Log.d(this.javaClass.simpleName, "Failure")
                System.out.println("Failure")
            }

            override fun onResponse(
                call: Call<List<UserResponse>>?,
                response: Response<List<UserResponse>>?
            ) {
                if (response?.body() != null)
                    data.value = response.body()!!
                System.out.println("Success" + response?.body())
            }
        })
        return data

    }

}