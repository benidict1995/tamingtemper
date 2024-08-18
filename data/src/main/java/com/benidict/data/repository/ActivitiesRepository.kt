package com.benidict.data.repository

import com.benidict.domain.model.LevelsDTO
import com.benidict.network.remote.ActivitiesRemoteSource
import javax.inject.Inject

class ActivitiesRepository @Inject constructor(
    private val activitiesRemoteSource: ActivitiesRemoteSource
){

    suspend fun loadActivities(): LevelsDTO {
        return activitiesRemoteSource.loadActivities()
    }
}