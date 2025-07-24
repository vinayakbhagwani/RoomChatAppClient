package com.vinayak.chatappdemo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MessageCardProperties(
    val senderName: String,
    val message: String,
    val color: Color
)

@Composable
fun MessageCard(
    messageCardProperties: MessageCardProperties
) {
    Spacer(modifier = Modifier.height(9.dp))
    Card(
        colors = CardDefaults.cardColors(containerColor = messageCardProperties.color),
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(messageCardProperties.senderName, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 19.sp)
            Text(messageCardProperties.message, color = Color.White, fontSize = 18.sp)
        }
    }
}