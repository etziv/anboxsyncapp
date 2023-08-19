package com.arcane.anboxsync.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.arcane.anboxsync.R
import com.arcane.anboxsync.ui.theme.AnBoxSyncTheme

@Composable
fun DialogBoxChatID(
    title: String,
    onDismiss: () -> Unit,
    onPositiveButtonClicked: () -> Unit,
    properties: DialogProperties = DialogProperties()
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = properties
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Row() {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color(0xff004aad),
                        modifier = Modifier
                            .clickable(onClick = onDismiss)
                    )
                }


                Spacer(modifier = Modifier.padding(top = 5.dp))
                
                Spacer(modifier = Modifier.padding(top = 20.dp))

                Text(
                    text = "Buka Telegram, cari “IDBot” atau menuju link t.me/myidbot di smartphone kamu.",
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 17.sp
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Image(
                    painter = painterResource(R.drawable.idbot),
                    contentDescription = "idbot",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp),
                )

                Spacer(modifier = Modifier.padding(top = 5.dp))

                Text(
                    text = "Start Bot lalu ketikan /getid",
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 17.sp
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.padding(top = 40.dp))

                Button(
                    onClick = onPositiveButtonClicked,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xff004aad),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Ok")
                }
            }
        }
    }
}

@Composable
fun DialogBoxToken(
    title: String,
    onDismiss: () -> Unit,
    onPositiveButtonClicked: () -> Unit,
    properties: DialogProperties = DialogProperties()
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = properties
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Row() {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color(0xff004aad),
                        modifier = Modifier
                            .clickable(onClick = onDismiss)
                    )
                }


                Spacer(modifier = Modifier.padding(top = 5.dp))

                Spacer(modifier = Modifier.padding(top = 20.dp))

                Text(
                    text = "Silahkan dapatkan token Box dengan mengscan QRCode pada Box Token Card",
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 17.sp
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.padding(top = 40.dp))

                Button(
                    onClick = onPositiveButtonClicked,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xff004aad),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Ok")
                }
            }
        }
    }
}

@Preview
@Composable
fun DialogBoxPreview() {
    AnBoxSyncTheme() {
        DialogBoxChatID(
            title = "Test",
            onDismiss = { /*TODO*/ },
            onPositiveButtonClicked = { /*TODO*/ })
    }
}

@Preview
@Composable
fun DialogBoxTokenPreview() {
    AnBoxSyncTheme() {
        DialogBoxToken(
            title = "Test",
            onDismiss = { /*TODO*/ },
            onPositiveButtonClicked = { /*TODO*/ })
    }
}