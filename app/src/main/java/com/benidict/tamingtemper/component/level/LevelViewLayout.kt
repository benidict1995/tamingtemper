package com.benidict.tamingtemper.component.level

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.domain.model.LevelDTO
import com.benidict.tamingtemper.R
import com.benidict.tamingtemper.component.activities.ActivityViewLayout
import com.benidict.tamingtemper.component.footer.FooterViewLayout
import com.benidict.tamingtemper.ui.theme.DarkHorse
import com.benidict.tamingtemper.ui.theme.Devil
import com.benidict.tamingtemper.ui.theme.euclidCircularFont

@Composable
fun LevelViewLayout(index: Int, size: Int, levelDTO: LevelDTO) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_chapter),
            contentDescription = ""
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(66.dp)
                .height(36.dp)
                .offset(y = (-6).dp)
                .clip(RoundedCornerShape(8.dp))
                .background(DarkHorse)
        ) {
            Text(
                fontFamily = euclidCircularFont,
                fontWeight = FontWeight.Medium,
                text = "LEVEL ${levelDTO.level}",
                color = Color.White,
                fontSize = 12.sp
            )
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Text(
            fontWeight = FontWeight.Bold,
            fontFamily = euclidCircularFont,
            fontSize = 18.sp,
            text = levelDTO.title
        )
        Spacer(
            modifier = Modifier.height(4.dp)
        )
        Text(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontFamily = euclidCircularFont,
            fontSize = 12.sp,
            color = Devil,
            text = levelDTO.description
        )
        LazyVerticalStaggeredGrid (
            modifier = Modifier.heightIn(max=1000.dp),
            columns = StaggeredGridCells.Fixed(2)) {
            items(levelDTO.activities) { item ->
                ActivityViewLayout(item)
            }
        }

//        if (index == (size-1)) {
//            FooterViewLayout()
//        }
    }
}