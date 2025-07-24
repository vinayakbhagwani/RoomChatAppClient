package com.vinayak.chatappdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    username: String,
    channel: String,
    onUsernameChange: (String) -> Unit,
    onChannelChange: (String) -> Unit,
    onJoin: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(32.dp), verticalArrangement = Arrangement.Center) {
        TextField(value = username, onValueChange = onUsernameChange, label = { Text("Username") })
        Spacer(Modifier.height(16.dp))
        TextField(value = channel, onValueChange = onChannelChange, label = { Text("Channel") })
        Spacer(Modifier.height(32.dp))
        Button(onClick = onJoin, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Join Chat")
        }
    }
}
