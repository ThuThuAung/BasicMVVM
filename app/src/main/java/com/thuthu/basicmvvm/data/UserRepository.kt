package com.thuthu.basicmvvm.data

import androidx.lifecycle.LiveData
import com.thuthu.basicmvvm.data.model.User
import com.thuthu.basicmvvm.data.model.UserResponse

interface UserRepository {
    fun getUserData() : LiveData<User>

    fun saveUser(user: User)

    fun searchUsers(query: User): LiveData<User>

    fun loadList(): LiveData<List<UserResponse>>
}