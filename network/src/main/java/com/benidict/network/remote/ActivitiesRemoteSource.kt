package com.benidict.network.remote

import android.content.Context
import com.benidict.domain.activitiesSource
import com.benidict.domain.model.LevelDTO
import com.benidict.domain.model.LevelsDTO
import com.benidict.domain.model.UserDTO
import com.benidict.domain.utilities.getFileFromAssets
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActivitiesRemoteSource @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {

    suspend fun loadActivities(): LevelsDTO {
        return withContext(Dispatchers.IO) {
            val filePath = getFileFromAssets(activitiesSource, context)
            val user = gson.fromJson(filePath.readText(), LevelsDTO::class.java)
            user
        }
    }
}