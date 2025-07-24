package com.vinayak.chatappdemo

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val from: String,
    val message: String
)
