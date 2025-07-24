package com.vinayak.chatappdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vinayak.chatappdemo.ui.theme.ChatAppDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatAppDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChatApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ChatApp() {
    var username by remember { mutableStateOf("") }
    var channel by remember { mutableStateOf("") }
    var inChat by remember { mutableStateOf(false) }

    val viewModel = remember { ChatViewModel() }

    if (!inChat) {
        LoginScreen(
            username = username,
            channel = channel,
            onUsernameChange = { username = it },
            onChannelChange = { channel = it },
            onJoin = {
                viewModel.connect(username, channel)
                inChat = true
            }
        )
    } else {
        ChatScreen(username, viewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChatAppDemoTheme {
        Greeting("Android")
    }
}