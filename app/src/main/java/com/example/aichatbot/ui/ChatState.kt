package com.example.aichatbot.ui

import android.graphics.Bitmap
import com.example.aichatbot.ui.data.Chat

data class ChatState
    (
    val chatList : MutableList<Chat> = mutableListOf(),
            val prompt : String  = "",
            val bitmap: Bitmap?= null
)