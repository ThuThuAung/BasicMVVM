package com.thuthu.basicmvvm.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import  com.thuthu.basicmvvm.data.UserRepository
import  com.thuthu.basicmvvm.data.UserRepositoryImpl
import  com.thuthu.basicmvvm.data.model.UserResponse

class DetailViewModel(private val repository: UserRepository = UserRepositoryImpl()) : ViewModel() {

    var name = ObservableField<String>("")
    var email = ObservableField<String>("")
    var phone = ObservableField<String>("")
    var address = ObservableField<String>("")

    fun onSetUpData(user: UserResponse) {
        name.set( "Name: " + user.mName)
        email.set( "Email: " + user.mEmail)
        phone.set( "Phone: " + user.phone)
        address.set( "Address: " + user.address?.city + " , " + user.address?.street)


    }

}