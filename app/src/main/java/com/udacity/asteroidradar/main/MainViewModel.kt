package com.udacity.asteroidradar.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.Repository
import com.udacity.asteroidradar.api.ApiInterface
import com.udacity.asteroidradar.api.RetrofitConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val picOfDay: MutableLiveData<PictureOfDay> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val asteroidAdapter = AsteroidAdapter()

    val api = RetrofitConfig().getClient()?.create(ApiInterface::class.java)

     fun getPicOfDay(c: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            val call = api?.getPicOfDay(c.getString(R.string.api_key))
            if (call!!.isSuccessful) {
                if (call.code() == 200 && call.body() != null)
                    picOfDay.setValue(call.body())
                else
                    error.setValue("error")
            } else error.setValue("error")
        }
    }

    fun getAsteroid(c: Context, isConnected: Boolean) {
        CoroutineScope(Dispatchers.Main).launch {
            asteroidAdapter.addServices(Repository(context = c).fetchData(isConnected))
        }
    }




}