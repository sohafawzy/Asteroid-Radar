package com.udacity.asteroidradar.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

class AsteroidViewModel : ViewModel() {
     val codeName = MutableLiveData<String>()
     val date = MutableLiveData<String>()
     val isPotentiallyHazardous = MutableLiveData<Boolean>()

    fun bind(asteroid: Asteroid) {
        codeName.value = asteroid.codename
        date.value = asteroid.closeApproachDate
        isPotentiallyHazardous.value = asteroid.isPotentiallyHazardous
    }


}