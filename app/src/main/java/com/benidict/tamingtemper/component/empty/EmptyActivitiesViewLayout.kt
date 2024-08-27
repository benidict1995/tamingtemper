package com.benidict.tamingtemper.component.empty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.benidict.tamingtemper.R
import com.benidict.tamingtemper.ui.theme.euclidCircularFont

@Composable
fun EmptyActivitiesViewLayout(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            fontWeight = FontWeight.Medium,
            fontFamily = euclidCircularFont,
            text = stringResource(R.string.no_activities_found)
        )
    }
}