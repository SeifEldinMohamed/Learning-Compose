package com.seif.learningcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.seif.learningcompose.ui.ui.theme.LearningComposeTheme
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class TimerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningComposeTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
//                        Timer(
//                            totalTime = 10 * 1000L,
//                            handleColor = Color.Green,
//                            inactiveBarColor = Color.Gray,
//                            activeBarColor = Color.Green,
//                            counterTextColor = Color.Black,
//                            counterTextSize = 44.sp,
//                            modifier = Modifier.size(200.dp, 200.dp),
//                        )
                        val navController = rememberNavController()
                        Scaffold(
                            bottomBar = {
                                BottomNavigationBar(
                                    items = listOf(
                                        BottomNavItem(
                                            name = "Home",
                                            route = "home",
                                            icon = Icons.Default.Home
                                        ),
                                        BottomNavItem(
                                            name = "Chat",
                                            route = "chat",
                                            icon = Icons.Default.Notifications,
                                            badgeCount = 10
                                        ),
                                        BottomNavItem(
                                            name = "Settings",
                                            route = "settings",
                                            icon = Icons.Default.Settings
                                        )

                                    ),
                                    navController = navController,
                                    onItemClick = {
                                                  navController.navigate(it.route)
                                    },
                                    backgroundColor = Color.DarkGray,
                                    selectedContentColor = Color.Green,
                                    unSelectedContentColor = Color.LightGray
                                )
                            }
                        ) {
                            Navigation(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("chat") {
            ChatScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
    }
}


@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home screen")
    }
}

@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Chat screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings screen")
    }
}


@androidx.compose.runtime.Composable
fun Timer(
    totalTime: Long,
    handleColor: androidx.compose.ui.graphics.Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: androidx.compose.ui.Modifier = Modifier,
    counterTextColor: Color,
    counterTextSize: androidx.compose.ui.unit.TextUnit,
    initialValue: Float = 1f,
    strokeWidth: androidx.compose.ui.unit.Dp = 5.dp,
    delayTime: Long = 100L,
    startAngle: Float = -215f,
    sweepAngle: Float = 250f,
    stopButtonColor: Color = Color.Red,
    startButtonColor: Color = Color.Green,
    stopButtonTextColor: Color = Color.Black,
    startButtonTextColor: Color = Color.Black
) {
    // size of whole composable
    var size by androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf(androidx.compose.ui.unit.IntSize.Zero)
    }
    // percentage value of our timer
    var value by remember {
        mutableStateOf(initialValue)
    }
    // actual time we are currently at
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    androidx.compose.runtime.LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0 && isTimerRunning) {
            kotlinx.coroutines.delay(delayTime)
            currentTime -= delayTime
            value = currentTime / totalTime.toFloat()
        }
    }
    androidx.compose.foundation.layout.Box(
        contentAlignment = androidx.compose.ui.Alignment.Center,
        modifier = modifier
            .onSizeChanged {
                size = it
            }
    ) {
        androidx.compose.foundation.Canvas(modifier = modifier) {
            drawArc(
                color = inactiveBarColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                size = androidx.compose.ui.geometry.Size(
                    size.width.toFloat(),
                    size.height.toFloat()
                ),
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
            drawArc(
                color = activeBarColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = androidx.compose.ui.graphics.StrokeCap.Round
                )
            )
            val center = androidx.compose.ui.geometry.Offset(size.width / 2f, size.height / 2f)
            val beta =
                (250f * value + 145f) * (kotlin.math.PI / 180f).toFloat() // angle, we use * (PI / 180f) to convert it to radians
            val radius = size.width / 2f
            val a = kotlin.math.cos(beta) * radius // first side
            val b = kotlin.math.sin(beta) * radius // second side

            drawPoints(
                listOf(Offset(center.x + a, center.y + b)),
                pointMode = androidx.compose.ui.graphics.PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(), // to make handle bigger than our arc
                cap = StrokeCap.Round
            )
        }
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = counterTextSize,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = counterTextColor
        )
        androidx.compose.material.Button(
            onClick = {
                if (currentTime <= 0L) {
                    currentTime = totalTime
                    isTimerRunning = true
                } else {
                    isTimerRunning = !isTimerRunning
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            colors = androidx.compose.material.ButtonDefaults.buttonColors(
                backgroundColor = if (!isTimerRunning || currentTime <= 0L) {
                    startButtonColor
                } else {
                    stopButtonColor
                }
            )
        ) {
            androidx.compose.material.Text(
                text = if (isTimerRunning && currentTime > 0L) "Stop"
                else if (!isTimerRunning && currentTime == totalTime) "Start"
                else if (!isTimerRunning && currentTime > 0L) "Resume"
                else "Restart",
                color = if (isTimerRunning && currentTime > 0L) stopButtonTextColor
                else if (!isTimerRunning && currentTime == totalTime) startButtonTextColor
                else if (!isTimerRunning && currentTime > 0L) startButtonTextColor
                else startButtonTextColor
            )
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    LearningComposeTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Timer(
                totalTime = 100L * 1000L,
                handleColor = Color.Black,
                inactiveBarColor = Color.Gray,
                activeBarColor = Color.Green,
                modifier = Modifier.size(200.dp),
                counterTextColor = Color.Black,
                counterTextSize = 44.sp,
                delayTime = 500L,
                stopButtonColor = Color.Black,
                stopButtonTextColor = Color.White,
                startButtonColor = Color.Cyan,
                startButtonTextColor = Color.Black
            )
        }
    }
}