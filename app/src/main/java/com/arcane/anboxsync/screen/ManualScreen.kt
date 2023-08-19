package com.arcane.anboxsync.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcane.anboxsync.components.BoxNote
import com.arcane.anboxsync.model.Note

@Composable
fun ManualScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text(
                        text = "Manual",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.padding(innerPadding)
        ){
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Konfigurasi Awal",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp
                    )
                )
                BoxNote(
                    text = "1. Jangan nyalakan Smart Box terlebih dahulu",
                    text2 = "2. Lakukan Box Settings dengan mengisi Box Token dan ChatID telegram" ,
                    text3 = "3. Jika sudah, simpan Box Settings dengan menekan tombol 'Save'",
                    text4 = "4. Nyalakan Smart Box dengan menghubungkannya ke sumber listrik",
                    text5 = "5. Tunggu hingga lcd muncul 'Scan your Package', Smart Box sudah dapat digunakan",
                )

            }
        }
    }
}

//@Preview
//@Composable
//fun WifiScrenPreview() {
//    AnBoxSyncTheme {
//        ManualScreen()
//    }
//}
