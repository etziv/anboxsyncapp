package com.arcane.anboxsync.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcane.anboxsync.R
import com.arcane.anboxsync.ui.theme.AnBoxSyncTheme

@Composable
fun CardButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
) {
    Card(
        modifier = modifier
            .padding(start = 15.dp, top = 5.dp, end = 15.dp, bottom = 15.dp)
            .height(80.dp)
            .shadow(
                elevation = 7.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color.Gray,
                spotColor = Color.DarkGray
            )
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.weight(1f)
            )
            Icon(imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = modifier.size(40.dp)
            )
        }
    }
}

@Composable
fun CardButtonTele(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .padding(start = 15.dp, top = 5.dp, end = 0.dp, bottom = 15.dp)
            .height(80.dp)
            .width(80.dp)
            .shadow(
                elevation = 7.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color.Gray,
                spotColor = Color.DarkGray
            )
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.telegram),
                contentDescription = "Tele Icon",
                modifier = Modifier
                    .size(50.dp)
            )
        }
    }
}

@Preview
@Composable
fun CardButtonPreview() {
    AnBoxSyncTheme() {
        CardButtonTele(onClick = { })
    }
}