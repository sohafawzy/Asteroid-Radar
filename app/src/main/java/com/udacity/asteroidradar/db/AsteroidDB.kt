package com.udacity.asteroidradar.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AsteroidEntity::class], version = 1)
abstract class AsteroidDB : RoomDatabase(){
        abstract fun asteroidDao(): AsteroidRaderDao
    companion object {
        private var INSTANCE: AsteroidDB? = null

        fun getDatabase(context: Context): AsteroidDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AsteroidDB::class.java,
                    "AsteroidDB"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}