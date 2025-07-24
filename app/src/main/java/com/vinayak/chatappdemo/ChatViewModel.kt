package com.vinayak.chatappdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages = _messages.asStateFlow()

    private var webSocket: WebSocket? = null

    fun connect(username: String, channel: String) {
        viewModelScope.launch {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("ws://10.0.2.2:9090/chat/$channel/$username")
                .build()

            webSocket = client.newWebSocket(request, object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    Log.d("WS", "Connected to WebSocket")
                }


                override fun onMessage(webSocket: WebSocket, text: String) {
                    Log.d("WS", "Received: $text")
                    val msg = Json.decodeFromString<ChatMessage>(text)
                    _messages.update { it + msg }
                }

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    Log.d("WS", "Closing: $code $reason")
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    Log.e("WS", "Error: ${t.message}", t)
                    response?.let {
                        Log.e("WS", "Server response: ${it.body?.string()}")
                    }
                }
            })
        }
    }

    fun sendMessage(text: String, from: String) {
        val msg = ChatMessage(from, text)
        val json = Json.encodeToString(msg)
        webSocket?.send(json)
    }

    override fun onCleared() {
        webSocket?.close(1000, "App closed")
        super.onCleared()
    }
}
