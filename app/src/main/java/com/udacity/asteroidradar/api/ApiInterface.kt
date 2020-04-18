package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.PictureOfDay
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("neo/rest/v1/feed")
    suspend fun getFeed(@Query("start_date") startDate : String,
                     @Query("end_date") endDate : String?,
                     @Query("api_key") apiKey : String) : Response<ResponseBody>

    @GET("planetary/apod")
    suspend fun getPicOfDay(@Query("api_key") apiKey : String) : Response<PictureOfDay>
}