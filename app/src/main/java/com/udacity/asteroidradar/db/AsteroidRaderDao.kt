package com.udacity.asteroidradar.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidRaderDao {
    @Query("SELECT * FROM asteroid_table Where closeApproachDate >= :date order by closeApproachDate")
    fun getAll(date: String): List<AsteroidEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<AsteroidEntity>)

    @Query("DELETE FROM asteroid_table")
    fun deleteAll()
}