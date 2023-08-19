package com.arcane.anboxsync.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcane.anboxsync.ui.theme.AnBoxSyncTheme

@Composable
fun GradientButton(
    onClick: () -> Unit,
    title: String,
    gradientColors: List<Color>,
    cornerRadius: Dp,
) {

    Button(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            onClick()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    brush = Brush.linearGradient(colors = gradientColors),
                    shape = RoundedCornerShape(cornerRadius)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun GradientButtonPreview() {
    AnBoxSyncTheme() {
        val gradientColors = listOf(
            Color(0xff004aad),
            Color(0xff5ce1e6)
        )
        GradientButton(title = "Save", onClick = { }, gradientColors = gradientColors, cornerRadius = 20.dp )
    }
}