package com.udacity.asteroidradar

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class PictureOfDay(@Json(name = "media_type") val mediaType: String, val title: String,
                        val url: String)