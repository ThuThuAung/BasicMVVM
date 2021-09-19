package com.thuthu.basicmvvm.data.db

import android.content.Context
import androidx.room.*
import  com.thuthu.basicmvvm.data.model.User

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {

            if (INSTANCE != null) return INSTANCE!!

            INSTANCE = Room
                .databaseBuilder(context, UserDatabase::class.java, "UserDatabase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

            return INSTANCE!!
        }
    }
}