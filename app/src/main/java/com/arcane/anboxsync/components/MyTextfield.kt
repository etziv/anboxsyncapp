package com.arcane.anboxsync.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CardDefaults.shape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.arcane.anboxsync.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.arcane.anboxsync.ui.theme.AnBoxSyncTheme

@Composable
fun TextfieldPass(
    value: String,
    onValueChange: (String) -> Unit,
    textFieldColors: Color,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = value ,
                onValueChange = onValueChange,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor =  textFieldColors,
                unfocusedIndicatorColor = textFieldColors),
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                maxLines = 1,
                textStyle = TextStyle.Default.copy(
                    fontSize = 17.sp,
                ),
            )
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible },

                ) {
                Icon(
                    painter = if (isPasswordVisible) painterResource(R.drawable.eye_see) else painterResource(R.drawable.eye_no_see),
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                )
            }
        }
    }
}

@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textFieldColors: Color
) {
    TextField(
        value = value ,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor =  textFieldColors, //hide the indicator
            unfocusedIndicatorColor = textFieldColors),
        shape = RoundedCornerShape(20.dp),
        singleLine = true,
        maxLines = 1,
        textStyle = TextStyle.Default.copy(
            fontSize = 17.sp,
        )
    )
}

//@Preview
//@Composable
//fun TextFieldPassPreview() {
//    AnBoxSyncTheme() {
//        var text by remember {
//            mutableStateOf("")
//        }
//        TextfieldPass(value = text, onValueChange = {text = it} )
//
//    }
//}


//@Preview
//@Composable
//fun TextFieldPreview() {
//    var text by remember {
//        mutableStateOf("")
//    }
//    MyTextField(
//        value = text,
//        onValueChange = {text = it}
//    )
//}
