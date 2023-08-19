package com.arcane.anboxsync.model

data class BoxData(
    var boxToken: String = "",
    var chatID: String = ""
) {
    constructor() : this("", "")
}