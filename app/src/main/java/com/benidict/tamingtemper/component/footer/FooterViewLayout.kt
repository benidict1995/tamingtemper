package com.benidict.tamingtemper.component.footer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.tamingtemper.R
import com.benidict.tamingtemper.ui.theme.RoyalBlue
import com.benidict.tamingtemper.ui.theme.SilverChalice
import com.benidict.tamingtemper.ui.theme.TamingtemperTheme
import com.benidict.tamingtemper.ui.theme.euclidCircularFont

@Composable
fun FooterViewLayout(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .then(modifier),
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(thickness = .5.dp, color = SilverChalice)
        Spacer(modifier = Modifier.height(25.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.ic_flag),
                contentDescription = ""
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontFamily = euclidCircularFont,
            fontSize = 12.sp,
            color = RoyalBlue,
            text = stringResource(R.string.journey)
        )
        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun FooterViewLayoutPreview() {
    TamingtemperTheme {
        FooterViewLayout(Modifier)
    }
}