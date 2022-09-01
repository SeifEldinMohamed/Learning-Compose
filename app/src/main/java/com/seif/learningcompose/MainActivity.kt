package com.seif.learningcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
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

            LazyColumnWithIndex()
        }
    }
}

@Composable
fun LazyColumnWithIndex(){
    LazyColumn(modifier = Modifier.padding(top = 10.dp)) {
        itemsIndexed(
            listOf("Hello", "Seif", "from", "Jetpack","Compose")
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
fun LazyColumn(){
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