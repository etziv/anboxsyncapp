package com.arcane.anboxsync.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcane.anboxsync.model.Note

@Composable
fun BoxNote(
    modifier: Modifier = Modifier,
    text: String,
    text2: String,
    text3: String,
    text4: String,
    text5: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = text
            )
            Text(
                text = text2
            )
            Text(
                text = text3
            )
            Text(
                text = text3
            )
            Text(
                text = text4
            )
            Text(
                text = text5
            )
        }
    }
}

//@Preview
//@Composable
//fun BoxNotePreview() {
//    BoxNote(text = "",)
//}