package com.thuthu.basicmvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(

    @SerializedName("street")
    @Expose
    var street: String? = null,
    @SerializedName("city")
    @Expose
    var city: String? = null,

    @SerializedName("geo")
    @Expose
    var geo: Geo? = null


) : Parcelable