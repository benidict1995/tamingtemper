package com.benidict.tamingtemper.component.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.benidict.domain.model.ActivitiesDTO
import com.benidict.tamingtemper.R
import com.benidict.tamingtemper.ui.theme.euclidCircularFont

@Composable
fun ActivityViewLayout(activitiesDTO: ActivitiesDTO) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        AsyncImage(
            error = painterResource(R.drawable.ic_activity),
            model = activitiesDTO.icon.file.url,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.padding(20.dp),
            fontSize = 14.sp,
            text = activitiesDTO.title,
            fontFamily = euclidCircularFont,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}