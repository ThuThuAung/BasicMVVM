package com.thuthu.basicmvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "password") val password: String? = null,
    @ColumnInfo(name = "email") val email: String? = null
)

