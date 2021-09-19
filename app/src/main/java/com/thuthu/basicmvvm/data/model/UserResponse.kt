package com.thuthu.basicmvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
 data class UserResponse (
     @SerializedName("id")
     @Expose
     var mId: Int? = null,
     @SerializedName("name")
     @Expose
     var mName: String? = null,

     @SerializedName("username")
     @Expose
     var mUserName: String? = null,

     @SerializedName("email")
     @Expose
     var mEmail: String? = null,

     @SerializedName("phone")
     @Expose
     var phone: String? = null,

    @SerializedName("address")
    @Expose
    var address: Address? = null
 ) : Parcelable

