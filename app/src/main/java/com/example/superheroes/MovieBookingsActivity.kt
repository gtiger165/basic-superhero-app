package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.superheroes.ui.theme.SuperheroesTheme

class MovieBookingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieBookingScreen("Android")
                }
            }
        }
    }
}

@Composable
fun MovieBookingScreen(name: String) {
    Surface(color = Color.White) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (menuButton, coverImage, titleText,
                genreText, ratingText, castText, castContainer,
                castImage1, castImage2, castImage3, castImage4, descText, bottomSurface,
                bookButton, cinemaNameContainer) = createRefs()

            Icon(
                painter = painterResource(id = R.drawable.baseline_menu_24),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
                    .constrainAs(menuButton) {
                        start.linkTo(parent.start, 16.dp)
                        top.linkTo(parent.top, 16.dp)
                    }
            )

            val rightGuideline = createGuidelineFromStart(0.4f)

            Image(
                painter = painterResource(id = R.drawable.android_superhero1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .constrainAs(coverImage) {
                        start.linkTo(parent.start, 24.dp)
                        top.linkTo(menuButton.bottom, 16.dp)
                        end.linkTo(rightGuideline, 16.dp)
                        width = Dimension.fillToConstraints
                    }
                    .aspectRatio(2f / 3f),
            )

            Text(
                text = "Deadpool",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.constrainAs(titleText) {
                    top.linkTo(menuButton.bottom, 8.dp)
                    start.linkTo(coverImage.end, 16.dp)
                }
            )

            Text(
                text = "Action | 1h 48m",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.constrainAs(genreText) {
                    top.linkTo(titleText.bottom, 8.dp)
                    start.linkTo(coverImage.end, 16.dp)
                }
            )

            Text(
                text = "IMDb 8.0/10",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.constrainAs(ratingText) {
                    top.linkTo(genreText.bottom, 16.dp)
                    start.linkTo(coverImage.end, 16.dp)
                }
            )

            Text(
                text = "CAST",
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.constrainAs(castText) {
                    bottom.linkTo(castContainer.top)
                    start.linkTo(coverImage.end, 16.dp)
                }
            )

            ConstraintLayout(modifier = Modifier.constrainAs(castContainer) {
                bottom.linkTo(coverImage.bottom)
                start.linkTo(coverImage.end, 16.dp)
                end.linkTo(parent.end, 16.dp)
                width = Dimension.fillToConstraints
            }) {
                Image(
                    painter = painterResource(id = R.drawable.android_superhero2),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .constrainAs(castImage1) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(coverImage.end)
                        }
                        .height(50.dp)
                        .aspectRatio(1f)
                )

                Image(
                    painter = painterResource(id = R.drawable.android_superhero3),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .constrainAs(castImage2) {
                            top.linkTo(castImage1.top)
                            bottom.linkTo(castImage1.bottom)
                            start.linkTo(castImage1.end)
                        }
                        .height(50.dp)
                        .aspectRatio(1f)
                )

                Image(
                    painter = painterResource(id = R.drawable.android_superhero4),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .constrainAs(castImage3) {
                            top.linkTo(castImage1.top)
                            bottom.linkTo(castImage1.bottom)
                            start.linkTo(castImage2.end)
                        }
                        .height(50.dp)
                        .aspectRatio(1f)
                )

                Box(
                    modifier = Modifier
                        .constrainAs(castImage4) {
                            top.linkTo(castImage1.top)
                            bottom.linkTo(castImage1.bottom)
                            start.linkTo(castImage3.end)
                        }
                        .height(50.dp)
                        .aspectRatio(1f)
                        .background(color = Color.Gray)
                ) {
                    Text(
                        text = "+9",
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .align(
                                Alignment.Center
                            ),
                        color = Color.Black
                    )
                }

                createHorizontalChain(
                    castImage1, castImage2, castImage3, castImage4,
                    chainStyle = ChainStyle.SpreadInside,
                )
            }

            val barrier = createBottomBarrier(coverImage, castContainer)

            Text(
                text = stringResource(R.string.movie_info),
                color = Color(0x8A000000),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(descText) {
                    top.linkTo(barrier, 36.dp)
                    start.linkTo(parent.start, 24.dp)
                    end.linkTo(parent.end, 24.dp)
                    width = Dimension.preferredWrapContent
                },
            )

            Surface(color = Color.Gray, modifier = Modifier.constrainAs(bottomSurface) {
                top.linkTo(descText.bottom, 36.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }) {}

            val (date1, date2, date3, date4, date5, date6, date7,
                day1, day2, day3, day4, day5, day6, day7, dateSelector, dateMarker) = createRefs()

            val rememberStartDateState = remember { mutableStateOf(day1.start) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperheroesTheme {
        MovieBookingScreen("Android")
    }
}