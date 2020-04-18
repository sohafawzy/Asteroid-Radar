package com.udacity.asteroidradar

import android.content.Context
import com.udacity.asteroidradar.api.ApiInterface
import com.udacity.asteroidradar.api.RetrofitConfig
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.db.AsteroidDB
import com.udacity.asteroidradar.db.AsteroidEntity
import com.udacity.asteroidradar.utils.RequestHelper
import com.udacity.asteroidradar.utils.Utils
import kotlinx.coroutines.*
import org.json.JSONObject

public class Repository(context: Context) {
    val utils: Utils
    val asteroidDB : AsteroidDB
    val  context : Context

    init {
        this.context = context
        utils = Utils(context)
        asteroidDB = AsteroidDB.getDatabase(context)
    }

    suspend fun fetchData(isConnected: Boolean) :List<Asteroid> {
        if (!isConnected || (isCacheFilled && isCacheFresh)) {
            return  fetchFromCache()
        } else {
            return refreshData()
        }
    }

    suspend fun fetchFromCache() : List<Asteroid>{
         return CoroutineScope(Dispatchers.IO).async{
            val  asteroidEntities = asteroidDB.asteroidDao().getAll()
            val  asteroids = arrayListOf<Asteroid>()
            for (s in asteroidEntities){
                asteroids.add(s.toAsteroid())
            }
            return@async asteroids
        }.await()
    }

    suspend fun refreshData() : List<Asteroid>{
        val api = RetrofitConfig().getClient()?.create(ApiInterface::class.java)
        val response = api?.getFeed(RequestHelper.start,RequestHelper.end,context.getString(R.string.api_key))
        if (response!!.isSuccessful) {
            if (response.code() == 200 && response.body() != null) {
                return GlobalScope.async(Dispatchers.IO) {
                    CoroutineScope(Dispatchers.IO).launch{
                        val  asteroids = parseAsteroidsJsonResult(JSONObject(response.body()?.string()))
                        val asteroidEntities = arrayListOf<AsteroidEntity>()
                        var asteroidEntity : AsteroidEntity
                        for (a in asteroids){
                            asteroidEntity = AsteroidEntity(a.codename,a.closeApproachDate,a.absoluteMagnitude,a.estimatedDiameter,a.relativeVelocity,a.distanceFromEarth,a.isPotentiallyHazardous)
                            asteroidEntity.id = a.id
                            asteroidEntities.add(asteroidEntity)
                        }
                        asteroidDB.asteroidDao().deleteAll()
                        asteroidDB.asteroidDao().insertAll(asteroidEntities)
                    }
                    return@async fetchFromCache()
                }.await()

            }
            else
                return fetchFromCache()
        } else
            return fetchFromCache()
    }

    private val isCacheFresh: Boolean
        get() {
            val cacheAge = System.currentTimeMillis() - utils.lastCacheUpdate
            return cacheAge < MAX_CACHE_AGE_IN_MILLIS
        }

    private val isCacheFilled: Boolean
        get() {
            return utils.lastCacheUpdate > 0L
        }



    companion object {
        const val MAX_CACHE_AGE_IN_MILLIS = 18000000
    }
}