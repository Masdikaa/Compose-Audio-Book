@file:OptIn(ExperimentalGlideComposeApi::class)

package com.masdika.composeaudiobook.ui

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.masdika.composeaudiobook.Book
import com.masdika.composeaudiobook.R
import com.masdika.composeaudiobook.Screen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MainScreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar()
        },

        modifier = modifier.fillMaxSize()

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 18.dp)
                .padding(vertical = 10.dp)
                .fillMaxSize()
        ) {
            PlayedNowCard(
                image = "https://i.pinimg.com/736x/52/ac/73/52ac731d6957581af4c887910a456b06.jpg",
                authorName = "Yuval Noah Harari",
                minutePlayedLeft = "8h 12m",
                audioBookTitle = "Sapiens, A Brief \nHistory of Humankind"
            )

            ChipSection(
                chip = listOf("All", "Roman", "Thriller", "Detective", "Fantasy")
            )

            val listOfBook = listOf(
                Book(
                    author = "Elizabeth Gilbert",
                    title = "City of Girls",
                    description = "Told from the perspective of an older woman as she looks back on her youth with both pleasure and regret (but mostly pleasure), City of Girls explores themes of female sexuality and promiscuity, as well as the idiosyncrasies of true love. Written with a powerful wisdom about human desire and connection, City of Girls is a love story like no other.",
                    image = "https://i.pinimg.com/236x/e8/4a/46/e84a463624e98ca3f4a4023b4c0f11e2.jpg",
                    rate = 4.8f,
                ),
                Book(
                    author = "Boris Akunin",
                    title = "The Diamond Chariot",
                    description = "The Diamond Chariot is a historical mystery novel by internationally acclaimed Russian detective story writer Boris Akunin, published originally in 2003. It is the tenth novel in Akunin's Erast Fandorin series of historical detective novels",
                    image = "https://i.pinimg.com/736x/ac/b4/58/acb458c6015b062adc18faecff9a535e.jpg",
                    rate = 4.7f
                ),
                Book(
                    author = "Masdika Ilhan Mansiz",
                    title = "Lorem Ipsum",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ullamco eum nisi hendrerit augue eleifend anim mollit tation eros possim enim blandit doming, laoreet stet elit pariatur kasd reprehenderit iriure iriure stet possim ea consetetur et sunt vel facilisi. Congue iure aliquam laboris facilisi dignissim veniam option amet eleifend adipisici duis accumsan erat labore cillum, tation autem dolore dolor elit accumsan imperdiet culpa. Excepteur erat dolore. Augue cupiditat ipsum.",
                    image = "https://i.pinimg.com/236x/e8/4a/46/e84a463624e98ca3f4a4023b4c0f11e2.jpg",
                    rate = 4.7f
                )
            )

            PlaylistsSection(listOfBook, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    val context = LocalContext.current
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
                        fontFamily = FontFamily(Font(R.font.gothampro)),
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
                                .makeText(context, "Search", Toast.LENGTH_SHORT)
                                .show()
                        },
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Search",
                )
            }
        },
    )
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    val navigationBarItems = remember { NavigationBarItems.entries.toTypedArray() }
    var selectedIndex by remember { mutableIntStateOf(0) }
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current

    AnimatedNavigationBar(
        modifier = modifier.height(64.dp),
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
    image: String,
    authorName: String,
    minutePlayedLeft: String,
    audioBookTitle: String,
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
            val topTextGuideline = createGuidelineFromTop(0.15f)
            val bottomTextGuideline = createGuidelineFromBottom(0.15f)

            GlideImage(
                modifier = Modifier.constrainAs(imageRef) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
                contentDescription = "Played",
                model = image,
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
                                Color.Black,
                                Color.Transparent,
                                Color.Black
                            ),
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
                    fontFamily = FontFamily(Font(R.font.gothampro)),
                    fontWeight = FontWeight.Thin,
                    color = Color.White
                ),
                fontSize = 24.sp
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
                    fontFamily = FontFamily(Font(R.font.gothampro)),
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
                    fontFamily = FontFamily(Font(R.font.gothampro)),
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                ),
                fontSize = 18.sp,
                text = buildAnnotatedString {
                    append(minutePlayedLeft)
                    withStyle(
                        style = SpanStyle(
                            color = Color.LightGray,
                            fontSize = 16.sp,
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
        mutableIntStateOf(0)
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
                animationSpec = tween(durationMillis = 400),
                label = "Chip Section Box Color Animation"
            )

            val selectedColorText by animateColorAsState(
                targetValue = if (selectedChipIndex == index) {
                    MaterialTheme.colorScheme.surface
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                animationSpec = tween(durationMillis = 500),
                label = "Chip Section Text Color Animation"
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

@Composable
fun PlaylistsSection(
    books: List<Book>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(books) { book ->
            PlaylistsItem(book = book) {
                val encodedUrl = encodeUrl(book.image)
                navController.navigate(
                    route = Screen.PlayScreen.withArgs(book.author, book.title, encodedUrl)
                )
            }
        }
    }
}

@Composable
fun PlaylistsItem(
    book: Book,
    onClick: (Book) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .clickable(interactionSource = interactionSource, indication = null) { onClick(book) },
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        ),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            val (bookImageRef, authorRef, titleRef, descriptionRef) = createRefs()

            Card(
                modifier = Modifier
                    .constrainAs(bookImageRef) {
                        start.linkTo(parent.start, margin = 15.dp)
                        top.linkTo(parent.top, margin = 15.dp)
                        bottom.linkTo(parent.bottom, margin = 15.dp)
                        width = Dimension.value(125.dp)
                        height = Dimension.value(156.25.dp)
                    },
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(2.dp),
            ) {
                GlideImage(
                    model = book.image,
                    contentDescription = "Book Cover",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = book.author,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.gothampro)),
                ),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.constrainAs(authorRef) {
                    top.linkTo(bookImageRef.top, margin = 12.dp)
                    start.linkTo(bookImageRef.end, margin = 15.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = book.title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.gothampro)),
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(authorRef.bottom, margin = 10.dp)
                    start.linkTo(bookImageRef.end, margin = 15.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = book.description,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.gothampro)),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                ),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .constrainAs(descriptionRef) {
                        bottom.linkTo(bookImageRef.bottom, margin = 12.dp)
                        start.linkTo(bookImageRef.end, margin = 15.dp)
                        end.linkTo(parent.end)
                        top.linkTo(titleRef.bottom, margin = 10.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                    .padding(end = 8.dp)
            )

        }

    }
}

// Navbar items
enum class NavigationBarItems(val icon: ImageVector) {
    Home(icon = Icons.Default.Home),
    Menu(icon = Icons.Default.Menu),
    Person(icon = Icons.Default.Person)
}

private fun encodeUrl(url: String): String {
    return URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
}