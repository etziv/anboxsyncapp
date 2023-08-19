@file:OptIn(ExperimentalMaterialApi::class)

package com.arcane.anboxsync

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arcane.anboxsync.components.CardButton
import com.arcane.anboxsync.components.CardButtonTele
import com.arcane.anboxsync.components.DialogBoxChatID
import com.arcane.anboxsync.components.DialogBoxToken
import com.arcane.anboxsync.components.MyTextField
import com.arcane.anboxsync.components.TextfieldPass
import com.arcane.anboxsync.components.GradientButton
import com.arcane.anboxsync.navigation.Screen
import com.arcane.anboxsync.preferences.AppPreferences
import com.arcane.anboxsync.screen.ManualScreen
import com.arcane.anboxsync.ui.theme.AnBoxSyncTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun AnboxsyncApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ){
        composable(route = Screen.MainScreen.route){
            MainScreen(navController)
        }
        composable(route = Screen.ManualScreen.route){
            ManualScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}



@Composable
fun MainScreen(
    navController: NavController
) {

    val database = Firebase.database
    val myRef = database.getReference("anboxsync")

    val context = LocalContext.current

    val appPreferences = AppPreferences(context)

    var boxToken by remember {
        mutableStateOf(appPreferences.boxToken ?: "")
    }
    var chatId by remember {
        mutableStateOf(appPreferences.chatId ?: "")
    }

    var isProcessing by remember { mutableStateOf(false) }

    fun saveButtonOnClick(appPreferences: AppPreferences) {
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    if (childSnapshot.key == boxToken) {
                        val childChatIdRef = childSnapshot.child("chatId").ref
                        childChatIdRef.setValue(chatId)
                            .addOnSuccessListener {
                                isProcessing = false
                                Toast.makeText(context, "Chat ID update successfully", Toast.LENGTH_SHORT).show()
                                appPreferences.boxToken = boxToken
                                appPreferences.chatId = chatId
                            }
                            .addOnFailureListener {
                                isProcessing = false
                                Toast.makeText(context, "Failed to update Chat ID", Toast.LENGTH_SHORT).show()
                            }
                        return // Keluar dari perulangan jika sudah ditemukan kecocokan
                    }
                }
                isProcessing = false
                // Jika tidak ada kecocokan ditemukan
                Toast.makeText(context, "No matching boxToken found", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    val gradientColors = listOf(
        Color(0xff004aad),
        Color(0xff5ce1e6)
    )

    //for box dialog
    var showDialogBoxToken by remember {
        mutableStateOf(false)
    }

    var showDialogChatID by remember {
        mutableStateOf(false)
    }

    var buttonPressed by remember { mutableStateOf(false) }

    val textFieldColorsBoxToken = if ((buttonPressed && boxToken.isBlank())) Color.Red else Color.Transparent

    val textFieldColorsChatID = if( buttonPressed && chatId.isBlank()) Color.Red else Color.Transparent

    //Lets define bottomSheetScaffoldState which will hold the state of Scaffold
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topEnd = 30.dp),
        sheetContent = {
            //Ui for bottom sheet
            Column(
                content = {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = "Anboxsync",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                        color = Color.White
                    )

                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                    ){
                        Text(
                            text = "Develop by Arcane",
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = gradientColors
                        ),
                        // shape = RoundedCornerShape(cornerRadius)
                    )
                    .padding(16.dp),

                )
        },
        sheetPeekHeight = 0.dp,

    ){
        if (showDialogBoxToken){
            DialogBoxToken(
                title = "Box Token",
                onDismiss = { showDialogBoxToken = false },
                onPositiveButtonClicked = { showDialogBoxToken = false })
        }

        if (showDialogChatID){
            DialogBoxChatID(
                title = "ChatID Telegram",
                onDismiss = { showDialogChatID = false },
                onPositiveButtonClicked = { showDialogChatID = false })
        }

        val context = LocalContext.current
        val webIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/anboxsync_bot"))

        //content
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(270.dp),
                ) {
                    Image(
                        painter = painterResource(R.drawable.boxblue),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(y = 1.dp)
                            .background(
                                Brush.verticalGradient(
                                    colorStops = arrayOf(
                                        Pair(0.7f, Color.Transparent),
                                        Pair(1f, MaterialTheme.colorScheme.background)
                                    )
                                )
                            )
                    )
                    Column(
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Text(
                            text = "Anboxsync",
                            modifier = Modifier
                                .padding(start = 20.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 40.sp
                            )
                        )
                        Text(
                            text = "Hai, I'm your partner for receiving packages :)",
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .width(250.dp),
                            style = TextStyle.Default.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                                color = Color.White
                            )
                        )
                    }
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                    bottomSheetScaffoldState.bottomSheetState.expand()
                                } else {
                                    bottomSheetScaffoldState.bottomSheetState.collapse()
                                }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(10.dp),
                    ){
                        Image(painter = painterResource(R.drawable.menu),
                            contentDescription = "Menu",
                        )
                    }

                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
                ) {
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "Box Settings",
                            modifier = Modifier.padding(start = 20.dp),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp
                            )
                        )
                        Card(
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxWidth()
                                .height(320.dp)
                                .shadow(
                                    elevation = 7.dp,
                                    shape = RoundedCornerShape(20.dp),
                                    ambientColor = Color.Gray,
                                    spotColor = Color.DarkGray
                                ),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                        ) {
                            Column(
                                modifier = Modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 20.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Box Token",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.Black
                                    )
                                    IconButton(
                                        onClick = {
                                            showDialogBoxToken = true
                                        }) {
                                        Image(painter = painterResource(R.drawable.info),
                                            contentDescription = "info token",
                                            alpha = 0.7f
                                        )
                                    }
                                }
                                TextfieldPass(
                                    value = boxToken,
                                    onValueChange = { boxToken = it },
                                    textFieldColors = textFieldColorsBoxToken,
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ){
                                    Text(
                                        text = "Telegram Chat ID",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.Black
                                    )
                                    IconButton(
                                        onClick = {
                                            showDialogChatID = true
                                        }) {
                                        Image(

                                            painter = painterResource(R.drawable.info),
                                            contentDescription = "info chatid",
                                            alpha = 0.7f
                                        )
                                    }
                                }

                                MyTextField(
                                    value = chatId,
                                    onValueChange = { chatId = it },
                                    textFieldColors = textFieldColorsChatID
                                )

                                Spacer(modifier = Modifier.padding(10.dp))

                                GradientButton(
                                    onClick =  {
                                        if (boxToken.isBlank() || chatId.isBlank()) {
                                            buttonPressed = true
                                        } else {
                                            isProcessing = true
                                            saveButtonOnClick(appPreferences)
                                        }
                                    },
                                    title = "Save",
                                    gradientColors = gradientColors,
                                    cornerRadius = 20.dp
                                )
                            }
                        }
                        Row() {
                            CardButtonTele(
                                onClick = { context.startActivity(webIntent) }
                            )
                            CardButton(
                                onClick = { navController.navigate(route = Screen.ManualScreen.route) },
                                title = "Manual"
                            )
                        }
                    }
                }
            }
            if (isProcessing) {
                CircularProgressIndicator(
                    color = Color(0xff5ce1e6),
                )
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    AnBoxSyncTheme {
        AnboxsyncApp()
    }
}