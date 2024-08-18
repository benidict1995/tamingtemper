package com.benidict.data.usecase

import com.benidict.data.repository.ActivitiesRepository
import com.benidict.domain.model.LevelsDTO
import javax.inject.Inject

class LoadActivitiesUseCase @Inject constructor(
    private val activitiesRepository: ActivitiesRepository
) {
    suspend fun loadActivities(): LevelsDTO {
        return activitiesRepository.loadActivities()
    }
}