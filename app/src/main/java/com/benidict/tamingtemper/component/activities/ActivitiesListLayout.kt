package com.benidict.tamingtemper.component.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.benidict.domain.model.LevelDTO
import com.benidict.tamingtemper.component.level.LevelViewLayout

@Composable
fun ActivitiesListLayout(levels: List<LevelDTO>) {
    LazyColumn {
        items(levels) { level ->
            LevelViewLayout(level)
        }
    }
}