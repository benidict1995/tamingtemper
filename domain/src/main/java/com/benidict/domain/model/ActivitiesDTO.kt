package com.benidict.domain.model

data class ActivitiesDTO (
    val id: String,
    val challengeId: String,
    val type: String,
    val title: String,
    val titleB: String,
    val description: String,
    val descriptionB: String,
    val state: String,
    val icon: IconDTO,
    val lockedIcon: IconDTO
)