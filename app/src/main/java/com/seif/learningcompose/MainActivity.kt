package com.seif.learningcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { // has all the composables that it should draw
            /*
            // states in compose
            Column(modifier = Modifier.fillMaxSize()) {
                val colorState = remember { // when this box is recomposed so we don't want to reset this value
                        mutableStateOf(Color.Yellow) // external state
                    }
                ColorBox(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ){
                    // this lambda block will be called when the colorState Changed (click on this box)
                    colorState.value = it
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .background(colorState.value)
                ) {

                }                
            }
             */
            /*
           //  buttons, textFields and sncakBars in compose
            Snackbar() {
                // we can use this SnackBar if we want to have full control on it (duration, where to show,...)
                Text(text = "Hello Seif")
            }

            val scaffoldState =
                rememberScaffoldState() // will give us the default state of that scaffold
            var textFieldState: String by remember {
                mutableStateOf("")
            }
            val scope = rememberCoroutineScope()

            Scaffold(
                modifier = Modifier.fillMaxWidth(),
                scaffoldState = scaffoldState
            ) {
                // it's a layout compose which will make it easy for us to include already existing material design components in compose
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp)
                ) {
                    TextField(
                        value = textFieldState,
                        label = {
                            Text(text = "Enter Your Greeting Name")
                        },
                        onValueChange = {
                            // called whenever text value changes (every character changed)
                            textFieldState = it
                        },
                        singleLine = true, // to have a single line.
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        scope.launch(Dispatchers.Main) {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "Hello $textFieldState",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }) {
                        // content of the button
                        Text(text = "Greeting Me")
                    }
                }
            }

             */
            Variable.simulateApiCall()

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
               // CircularProgressBar(percentage = 0.8f, number = 100)
               // Navigation()
                ReposListScreen()
            }
        }
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    /*
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
     */ // philip's way

    val currentPercentage = remember { Animatable(0f) } // This Animatable function creates a float value holder that automatically animates its value when the value is changed via animateTo
    LaunchedEffect(percentage) {
        currentPercentage.animateTo(
            percentage,
            animationSpec = tween(durationMillis = animDuration, delayMillis = animDelay)
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = color,
                startAngle = 270f,
                sweepAngle = 360 * currentPercentage.value, // the animation angle
                useCenter = false, // it won't connect center lines with the center (if true then we will have a pie)
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round // caps: descripes the end of our stroke to make them round
                )
            )
        }
        Text(
            text = (currentPercentage.value * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun SimpleAnimation() {
    var sizeSateValue by remember {
        mutableStateOf(200.dp)
    }
    val size by animateDpAsState(
        targetValue = sizeSateValue,

        tween(
            durationMillis = 2000,
            delayMillis = 300,
            easing = LinearOutSlowInEasing // default (control speed of the animation)
        ) // apply a simple animation curve to our animation

        /*
        spring(
            Spring.DampingRatioHighBouncy
        ) // apply simple spring animation

         */
    )
    val inflationTransition = rememberInfiniteTransition()
    val color by inflationTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier
            .size(size)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeSateValue += 50.dp
        }) {
            Text(text = "Increase Size")
        }
    }
}

@Composable
fun Test() {
    var i = 0
    var text by remember {
        mutableStateOf("#")
    }
    LaunchedEffect(key1 = true) {
        delay(1000L)
    }
    Button(onClick = { text += '#' }) {
        i++
        Text(text = text)
    }
}

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (minWidth < 600.dp) {
            decoupledConstraints(margin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 162.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        // Create references for the composables to constrain
        val (button, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button")
        }

        // Assign reference "text" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)
        })
    }
}

@Composable
fun PhilipConstraintLayout() {
    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")
        val guideline = createGuidelineFromTop(0.5f)
        constrain(greenBox) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        constrain(redBox) {
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        // createHorizontalChain(greenBox, redBox)
    }
    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .background(Color.Green)
                .layoutId("greenBox")
        )
        Box(
            modifier = Modifier
                .background(Color.Red)
                .layoutId("redBox")
        )
    }
}

@Composable
fun LazyColumnWithIndex() {
    LazyColumn(modifier = Modifier.padding(top = 10.dp)) {
        itemsIndexed(
            listOf("Hello", "Seif", "from", "Jetpack", "Compose")
        ) { index, string ->
            Text(
                text = "$index- $string",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp)
            )
        }
    }
}

@Composable
fun LazyColumn() {
    LazyColumn(modifier = Modifier.padding(top = 10.dp)) {
        items(1000) {
            Text(
                text = "text $it",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp)
            )
        }
    }
}

@Composable
fun ScrollableColumn() {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        for (i in 0..50) {
            Text(
                text = "text $i",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp)
            )
        }
    }
}

@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit
) {
    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    alpha = 1f
                )
            )
        }
    ) {
    }
}

@Composable
fun ImageCard(
    painter: Painter, // allow us to use image from our image resource and display that in our image card
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(200.dp)
            .height(220.dp)
            .padding(20.dp),
        shape = RoundedCornerShape(14.dp),
        elevation = 6.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxHeight()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            )
        } // a container that we can put composables into that and just apply a modifier(each item we added inside that box will be stacked on top of each other and can align them as well)

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() { // we can preview our composable
    ConstraintLayoutContent()
}

// there is no margin in compose as we can do all the work with padding
// offset(): it will offset the element just like margin does it but it won't push away other elements,
// it always starts from top left corner of our composable

// recomposed: Means that the composable will be redrawn again
// state: it's just a value that can change over time in the end
// with larger apps we will handle the states in viewModel

// the second example in state is called: State hoisting
// State hoisting in Compose is a pattern of moving state to a composable's caller to make a composable stateless.
// The general pattern for state hoisting in Jetpack Compose is to replace the state variable with two parameters:

// it provides a layout which will make it easy for us to include already existing material design components in compose (as toolbar, navigationDrawer, snackBar)

/** Warning:
 * Never launch a coroutine directly in the composable, it's only ok in callbacks such as onClickListener
 **/

// Modifier.verticalScroll() : we nedd a scroll state so we can manipulate the current scroll position to programmatically scroll in our column
// lazy columns : lazily loads this items as we scroll

// fullScreen: WindowCompat.setDecorFitsSystemWindows(window, false)

// ConstraintLayout in Compose works in the following way using a DSL:
//
// Create references for each composable in the ConstraintLayout using the createRefs() or createRefFor()
// Constraints are provided using the constrainAs() modifier, which takes the reference as a parameter and lets you specify its constraints in the body lambda.
// Constraints are specified using linkTo() or other helpful methods.
// parent is an existing reference that can be used to specify constraints towards the ConstraintLayout composable itself.

// Dimension.fillToConstraints == 0.dp
// createGuidelineFromStart() : Vertical GuideLine form start
// createGuidelineFromTop() : Horizontal GuideLine form top
/**
// Create guideline from the start of the parent at 10% the width of the Composable
val startGuideline = createGuidelineFromStart(0.1f)
// Create guideline from the end of the parent at 10% the width of the Composable
val endGuideline = createGuidelineFromEnd(0.1f)
//  Create guideline from 16 dp from the top of the parent
val topGuideline = createGuidelineFromTop(16.dp)
//  Create guideline from 16 dp from the bottom of the parent
val bottomGuideline = createGuidelineFromBottom(16.dp)
 **/

// Barriers:
// Barriers reference multiple composables to create a virtual guideline based on the most
// extreme widget on the specified side.
/** val topBarrier = createTopBarrier(button, text) **/

// Chains:
// Chains provide group-like behavior in a single axis (horizontally or vertically) .
// The other axis can be constrained independently.
/**
val verticalChain = createVerticalChain(button, text, chainStyle = ChainStyle.Spread)
val horizontalChain = createHorizontalChain(button, text)
 **/

// The chain can then be used in the Modifier.constrainAs() block.
//
// A chain can be configured with different ChainStyles, which decide how to deal with the space surrounding a composable, such as:
//
// ChainStyle.Spread: Space is distributed evenly across all the composables, including free space before the first composable and after the last composable.
// ChainStyle.SpreadInside: Space is distributed evenly across the all composables, without any free space before the first composable or after the last composable.
// ChainStyle.Packed: Space is distributed before the first and after the last composable, composables are packed together without space in between each other.

// Decoupled API
//In the ConstraintLayout example, constraints are specified inline, with a modifier in the composable they're applied to. However, there are situations when it's preferable to decouple the constraints from the layouts they apply to. For example, you might want to change the constraints based on the screen configuration, or animate between two constraint sets.
//
//For cases like these, you can use ConstraintLayout in a different way:
//
//Pass in a ConstraintSet as a parameter to ConstraintLayout.
//Assign references created in the ConstraintSet to composables using the layoutId modifier.

/** Effect Handlers **/
// side Effect: they are something that escapes the scope of a composable function (api call, counter, ... in compose fun)
// 1- LaunchedEffect: we get coroutine scope block so we can use suspend function in it.
/** LaunchedEffect(key1 = , block = ) **/
// whenever this key changes (maybe a state), the LaunchedEffect will cancel this coroutine scope and relaunch it with the new value