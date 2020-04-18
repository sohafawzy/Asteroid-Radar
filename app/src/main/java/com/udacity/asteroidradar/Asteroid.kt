package com.udacity.asteroidradar

import android.os.Parcelable
import com.udacity.asteroidradar.db.AsteroidEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
public data class Asteroid(val id: Long, val codename: String, val closeApproachDate: String,
                    val absoluteMagnitude: Double, val estimatedDiameter: Double,
                    val relativeVelocity: Double, val distanceFromEarth: Double,
                    val isPotentiallyHazardous: Boolean) : Parcelable