package com.benidict.tamingtemper.component.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.benidict.tamingtemper.R
import com.benidict.tamingtemper.ui.theme.RoyalBlue
import com.benidict.tamingtemper.ui.theme.TamingtemperTheme
import com.benidict.tamingtemper.ui.theme.euclidCircularFont

@Composable
fun HeaderViewLayout(
    onLogOut: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.padding(top = 32.dp).fillMaxWidth()
    ) {
        val (ivLogo, tvAppName, linearProgress, tvPercent, rowProfile) = createRefs()
        Image(
            modifier = Modifier.constrainAs(ivLogo) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            },
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.constrainAs(tvAppName) {
                top.linkTo(parent.top, margin = 4.dp)
                start.linkTo(ivLogo.end, margin = 12.dp)
            },
            fontSize = 16.sp,
            fontFamily = euclidCircularFont,
            fontWeight = FontWeight.SemiBold,
            text = stringResource(R.string.taming_temper)
        )
        LinearProgressIndicator(
            modifier = Modifier
                .height(4.dp)
                .width(72.dp)
                .constrainAs(linearProgress) {
                    bottom.linkTo(ivLogo.bottom, 4.dp)
                    start.linkTo(ivLogo.end, margin = 12.dp)
                },
            progress = 0.5f
        )
        Text(
            modifier = Modifier.constrainAs(tvPercent) {
                top.linkTo(linearProgress.top)
                bottom.linkTo(linearProgress.bottom)
                start.linkTo(linearProgress.end, 6.dp)
            },
            fontFamily = euclidCircularFont,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            color = RoyalBlue,
            text = stringResource(R.string.percent)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.constrainAs(rowProfile) {
                end.linkTo(parent.end)
                top.linkTo(ivLogo.top)
                bottom.linkTo(ivLogo.bottom)
            }
        ) {
            Image(
                painter = painterResource(R.drawable.ic_fire),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                fontFamily = euclidCircularFont,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = RoyalBlue,
                text = stringResource(R.string.zero)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                modifier = Modifier.clickable { onLogOut() },
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = ""
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HeaderViewLayoutPreview() {
    TamingtemperTheme {
        HeaderViewLayout {

        }
    }
}