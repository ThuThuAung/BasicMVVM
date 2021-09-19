package com.thuthu.basicmvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Geo(

    @SerializedName("lat")
    @Expose
    var lat: String? = null,
    @SerializedName("lng")
    @Expose
    var lng: String? = null


): Parcelable