package com.udacity.asteroidradar

import android.content.Context
import androidx.work.CoroutineWorker

import androidx.work.WorkerParameters
import retrofit2.HttpException

public class RefreshDataWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {
    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val repository = Repository(applicationContext)
        return try {
            repository.getNewData()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}