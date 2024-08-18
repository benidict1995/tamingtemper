package com.benidict.domain.model

data class LevelsDTO (
    val levels: List<LevelDTO>
)

data class LevelDTO (
    val level: String,
    val title: String,
    val description: String,
    val state: String,
    val activities: List<ActivitiesDTO>
)