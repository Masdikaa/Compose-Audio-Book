package com.masdika.composeaudiobook.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.masdika.composeaudiobook.R
import com.masdika.composeaudiobook.ui.theme.LocalExtendedColorScheme
import com.masdika.composeaudiobook.ui.theme.crystalBlueDark
import com.masdika.composeaudiobook.ui.theme.onCrystalBlueContainerDark

@Composable
fun PlayScreen(
    author: String,
    title: String,
    image: String,
) {
    val extendedColors = LocalExtendedColorScheme.current

    Scaffold(
        containerColor = extendedColors.crystalBlue.color,
        bottomBar = {
            BottomSection()
        }
    ) { innerPadding ->
//        Text(
//            text = "$author \n\n$title \n\n$image",
//            modifier = Modifier.padding(innerPadding)
//        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(crystalBlueDark)
        ) {
            val (titleRef, imageRef) = createRefs()

        }

    }
}

@Composable
fun BottomSection() {
    Box(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .background(onCrystalBlueContainerDark)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.bookmark_icon),
                contentDescription = "Bookmark",
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.plus_icon),
                contentDescription = "Bookmark",
                modifier = Modifier
                    .height(26.dp)
                    .width(26.dp),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.volume_icon),
                contentDescription = "Bookmark",
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}