package com.masdika.composeaudiobook.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlayScreen(
    author: String,
    title: String,
    image: String,
) {
    Scaffold { innerPadding ->
        Text(text = "$author \n\n$title \n\n$image", modifier = Modifier.padding(innerPadding))
    }
}