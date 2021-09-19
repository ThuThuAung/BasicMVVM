package com.thuthu.basicmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import  com.thuthu.basicmvvm.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: User)


    @Query("select * from user")
    fun getAll(): LiveData<User>

    @Query("select * from user WHERE email LIKE :email AND password LIKE :password")
    fun getUser(email: String?, password: String?): LiveData<User>


}
