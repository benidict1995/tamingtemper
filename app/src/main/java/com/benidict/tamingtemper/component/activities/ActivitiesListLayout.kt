package com.benidict.tamingtemper.component.activities

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benidict.domain.model.LevelDTO
import com.benidict.tamingtemper.component.level.LevelViewLayout

@Composable
fun ActivitiesListLayout(modifier: Modifier, levels: List<LevelDTO>) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(levels) { index, level ->
            LevelViewLayout(index, levels.size, level)
        }
    }
}