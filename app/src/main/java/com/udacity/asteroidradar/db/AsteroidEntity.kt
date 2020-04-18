package com.udacity.asteroidradar.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.Asteroid

@Entity(tableName = "asteroid_table")
class AsteroidEntity(val codename: String, val closeApproachDate: String,
                     val absoluteMagnitude: Double, val estimatedDiameter: Double,
                     val relativeVelocity: Double, val distanceFromEarth: Double,
                     val isPotentiallyHazardous: Boolean){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun toAsteroid() = Asteroid(
        id,
        codename,
        closeApproachDate,
        absoluteMagnitude,
        estimatedDiameter,
        relativeVelocity,
        distanceFromEarth,
        isPotentiallyHazardous
    )

}