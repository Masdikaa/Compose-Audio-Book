package com.masdika.composeaudiobook

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.masdika.composeaudiobook.ui.theme.ComposeAudioBookTheme

class MainActivity : ComponentActivity() {

    private val fontFamily = FontFamily(
        Font(R.font.gothampro, FontWeight.Normal),
        Font(R.font.gothampro_black, FontWeight.Black),
        Font(R.font.gothampro_bold, FontWeight.Bold),
        Font(R.font.gothampro_light, FontWeight.Light),
        Font(R.font.gothampro_medium, FontWeight.Medium)
    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ComposeAudioBookTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                titleContentColor = MaterialTheme.colorScheme.onSurface,
                            ),
                            title = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(end = 18.dp)
                                ) {
                                    Text(
                                        text = "Read Me",
                                        style = TextStyle(
                                            fontFamily = fontFamily,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold,
                                        ),
                                    )

                                    Icon(
                                        modifier = Modifier
                                            .height(24.dp)
                                            .width(24.dp)
                                            .clickable {
                                                Toast
                                                    .makeText(this@MainActivity, "Search", Toast.LENGTH_SHORT)
                                                    .show()
                                            },
                                        painter = painterResource(id = R.drawable.search_icon),
                                        contentDescription = "Search",
                                    )

                                }

                            },
                        )
                    },

                    bottomBar = {
                        BottomBar()
                    },

                    modifier = Modifier.fillMaxSize()
                )

                { innerPadding ->

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 18.dp)
                            .padding(vertical = 10.dp)
                            .fillMaxSize()
                    ) {

                        PlayedNowCard(
                            image = painterResource(id = R.drawable.sample),
                            authorName = "Yuval Noah Harari",
                            minutePlayedLeft = "8h 12m",
                            audioBookTitle = "Sapiens, A Brief \nHistory of Humankind",
                            fontFamily = fontFamily
                        )

                        ChipSection(
                            chip = listOf("All", "Roman", "Thriller", "Detective", "Fantasy")
                        )

                    }

                }
            }
        }
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    val navigationBarItems = remember { NavigationBarItems.values() }
    var selectedIndex by remember { mutableStateOf(0) }
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current

    AnimatedNavigationBar(
        modifier = Modifier.height(64.dp),
        selectedIndex = selectedIndex,
        cornerRadius = shapeCornerRadius(20.dp),
        ballAnimation = Straight(tween(200)),
        indentAnimation = Height(tween(200)),
        barColor = MaterialTheme.colorScheme.surface,
        ballColor = MaterialTheme.colorScheme.onSurface,
    ) {
        navigationBarItems.forEach { item ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(interactionSource = interactionSource, indication = null) {
                        selectedIndex = item.ordinal
                        Toast
                            .makeText(context, "Index : $selectedIndex", Toast.LENGTH_SHORT)
                            .show()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(26.dp),
                    imageVector = item.icon,
                    contentDescription = "Bottom Bar Icon",
                    tint = if (selectedIndex == item.ordinal) MaterialTheme.colorScheme.onSurface else Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun PlayedNowCard(
    modifier: Modifier = Modifier,
    image: Painter,
    authorName: String,
    minutePlayedLeft: String,
    audioBookTitle: String,
    fontFamily: FontFamily
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            val (imageRef, authorNameRef, minutePlayedLeftRef, audioBookTitleRef, gradientBoxRef) = createRefs()
            val startTextGuideline = createGuidelineFromStart(0.1f)
            val endTextGuideline = createGuidelineFromEnd(0.1f)
            val topTextGuideline = createGuidelineFromTop(0.2f)
            val bottomTextGuideline = createGuidelineFromBottom(0.2f)

            Image(
                modifier = Modifier.constrainAs(imageRef) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
                contentDescription = "Played",
                painter = image,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .constrainAs(gradientBoxRef) {
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.4f),
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.4f)
                            ),
                            // startY = 300f
                        )
                    )
            )

            Text(
                modifier = Modifier.constrainAs(audioBookTitleRef) {
                    width = Dimension.fillToConstraints
                    bottom.linkTo(bottomTextGuideline)
                    start.linkTo(startTextGuideline)
                    end.linkTo(endTextGuideline)
                },
                text = audioBookTitle,
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Thin,
                    color = Color.White
                ),
                fontSize = 23.sp
            )

            Text(
                modifier = Modifier.constrainAs(authorNameRef) {
                    width = Dimension.fillToConstraints
                    top.linkTo(topTextGuideline)
                    start.linkTo(startTextGuideline)
                    end.linkTo(endTextGuideline)
                },
                text = authorName,
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                ),
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier.constrainAs(minutePlayedLeftRef) {
                    width = Dimension.fillToConstraints
                    top.linkTo(authorNameRef.bottom, margin = 5.dp)
                    start.linkTo(startTextGuideline)
                    end.linkTo(endTextGuideline)
                },
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                ),
                fontSize = 16.sp,
                text = buildAnnotatedString {
                    append(minutePlayedLeft)
                    withStyle(
                        style = SpanStyle(
                            color = Color.LightGray,
                            fontSize = 14.sp,
                        )
                    ) {
                        append(" left")
                    }
                }
            )
        }

    }
}

@Composable
fun ChipSection(chip: List<String>, modifier: Modifier = Modifier) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    val interactionSource = remember { MutableInteractionSource() }

    LazyRow(modifier.fillMaxWidth()) {
        items(chip.size) { index ->

            val selectedColor by animateColorAsState(
                targetValue = if (selectedChipIndex == index) {
                    MaterialTheme.colorScheme.onSurface
                } else {
                    MaterialTheme.colorScheme.surface
                },
                animationSpec = tween(durationMillis = 400)
            )

            val selectedColorText by animateColorAsState(
                targetValue = if (selectedChipIndex == index) {
                    MaterialTheme.colorScheme.surface
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                animationSpec = tween(durationMillis = 500)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 15.dp, end = 10.dp)
                    .clickable(interactionSource = interactionSource, indication = null) {
                        selectedChipIndex = index
                    }
                    .clip(RoundedCornerShape(20.dp))
                    .background(selectedColor)
                    .padding(10.dp)
            ) {
                Text(
                    text = chip[index],
                    style = TextStyle(
                        color = selectedColorText,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.gothampro)),
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

enum class NavigationBarItems(val icon: ImageVector) {
    Home(icon = Icons.Default.Home),
    Menu(icon = Icons.Default.Menu),
    Person(icon = Icons.Default.Person)
}