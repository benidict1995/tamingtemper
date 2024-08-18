package com.benidict.tamingtemper.component.activities

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.benidict.domain.model.LevelDTO
import com.benidict.tamingtemper.component.level.LevelViewLayout

@Composable
fun ActivitiesListLayout(levels: List<LevelDTO>) {
    LazyColumn {
        itemsIndexed(levels) { index, level ->
            LevelViewLayout(index, levels.size, level)
        }
    }
}