package com.masdika.composeaudiobook.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.masdika.composeaudiobook.R
import com.masdika.composeaudiobook.ui.theme.LocalExtendedColorScheme
import com.masdika.composeaudiobook.ui.theme.crystalBlueDark
import com.masdika.composeaudiobook.ui.theme.onCrystalBlueContainerDark
import com.masdika.composeaudiobook.ui.theme.surfaceDimLightHighContrast

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
        },
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
            val topSectionRef = createRef()
            val bookImageRef = createRef()
            val playTimeRef = createRef()
            val topConstraintGuide = createGuidelineFromTop(0.05f)

            TopSection(
                modifier = Modifier.constrainAs(topSectionRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(bookImageRef.top)
                })

            BookImage(
                modifier = Modifier.constrainAs(bookImageRef) {
                    start.linkTo(parent.start, margin = 15.dp)
                    end.linkTo(parent.end, margin = 15.dp)
                    top.linkTo(topConstraintGuide)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                },
                imageUrl = image
            )

            Box(
                modifier = Modifier
                    .constrainAs(playTimeRef) {
                        top.linkTo(bookImageRef.bottom, margin = 10.dp)
                        start.linkTo(bookImageRef.start)
                        end.linkTo(bookImageRef.end)
                        height = Dimension.wrapContent
                        width = Dimension.fillToConstraints
                    }
            ) {
                PlayTimeSection()
            }

        }

    }
}

@Composable
fun TopSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(60.dp)
            .height(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.elevatedCardElevation(0.dp),
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(surfaceDimLightHighContrast)
        ) {}
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookImage(modifier: Modifier = Modifier, imageUrl: String) {
    Card(
        modifier = modifier
            .width(353.dp)
            .height(400.dp),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        GlideImage(
            model = imageUrl,
            contentDescription = "Book Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayTimeSection(modifier: Modifier = Modifier) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    val totalSeconds = (sliderPosition * 3600).toInt()
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    val formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds)

    Column(modifier = modifier.fillMaxWidth()) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                activeTrackColor = Color.Black,
                inactiveTrackColor = Color.Black.copy(alpha = 0.2f),
            ),
            valueRange = 0f..10f,
            thumb = {
                Box(modifier = Modifier.background(Color.Transparent))
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
        ) {
            Text(text = formattedTime)
            Text(text = "10:00:00")
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