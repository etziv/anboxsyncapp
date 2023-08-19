package com.arcane.anboxsync.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomSheetItem(
    val title: String,
    val icon: ImageVector
)

val bottomSheetItems = listOf(
    BottomSheetItem(title = "Notification", icon = Icons.Default.Notifications),
    BottomSheetItem(title = "Mail", icon = Icons.Default.MailOutline),
    BottomSheetItem(title = "Scan", icon = Icons.Default.Search),
    BottomSheetItem(title = "Edit", icon = Icons.Default.Edit),
    BottomSheetItem(title = "Favorite", icon = Icons.Default.Favorite),
    BottomSheetItem(title = "Settings", icon = Icons.Default.Settings)
)