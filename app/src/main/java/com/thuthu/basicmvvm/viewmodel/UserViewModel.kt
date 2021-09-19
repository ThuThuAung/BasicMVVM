package com.thuthu.basicmvvm.viewmodel

import android.util.Patterns
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import  com.thuthu.basicmvvm.data.UserRepository
import  com.thuthu.basicmvvm.data.UserRepositoryImpl
import  com.thuthu.basicmvvm.data.model.User
import java.util.*


class UserViewModel(private val repository: UserRepository = UserRepositoryImpl()) : ViewModel() {

    var email = ObservableField<String>("")
    var password = ObservableField<String>("")
    var onChecked = false

    private val mUserData = MediatorLiveData<User>()
    private val allUserData = MediatorLiveData<User>()
    private val saveLiveData = MutableLiveData<Boolean>()
    private val mError = MutableLiveData<String>()

    private val mSpinnerData = MutableLiveData<List<String>>()

    init {
        getAllUser()
    }

    fun getUserData(): LiveData<User> = mUserData
    fun getError(): LiveData<String> = mError

    fun getInsertLiveData(): LiveData<Boolean> = saveLiveData

    fun getAllUser() {
        mUserData.addSource(repository.getUserData()) { users ->
            allUserData.postValue(users)
        }
    }

    fun fetchSpinnerItems(list: ArrayList<String>): LiveData<List<String>> {
        mSpinnerData.value = list
        return mSpinnerData
    }


    fun searchUser(query: User) : LiveData<User> {
        return repository.searchUsers(query)
    }

    fun onInsertUserData(emailList: Array<String>) {
        if (emailList.size > 0 ){
            for (i in emailList) {
                // body of loop
                repository.saveUser(User(email = i, password = "1234567"))
            }
            saveLiveData.postValue(true)
        } else {
            saveLiveData.postValue(false)
        }
    }

    fun onClickLogin(view: View) {

        if(!isEmailValid()) {
            mError.postValue("Invalid Email!")
            return
        }
        if(!isPasswordValid()){
            mError.postValue("Invalid Password!")
            return
        }

        mUserData.addSource(searchUser(User(email = email.get(), password = password.get()))) { users ->
            if(users == null)
                mError.postValue("Invalid Login!")
            else
                mUserData.postValue(users)
        }


    }

    fun isEmailValid(): Boolean {
        val email = this.email.get()
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches() && email != ""

    }

    fun isPasswordValid(): Boolean {
        val password = this.password.get()
        return password != null && password.length> 5 && password != ""
    }

}