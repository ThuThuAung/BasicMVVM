package com.thuthu.basicmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import  com.thuthu.basicmvvm.data.UserRepository
import  com.thuthu.basicmvvm.data.UserRepositoryImpl
import  com.thuthu.basicmvvm.data.model.UserResponse

class ListViewModel(private val repository: UserRepository = UserRepositoryImpl()): ViewModel()  {

    fun loadList(): LiveData<List<UserResponse>> {
        return repository.loadList()
    }

}