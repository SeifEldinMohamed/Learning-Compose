package com.seif.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // has all the composables that it should draw
            val painters = painterResource(id = R.drawable.nyc_in_snow)
            val description = "New York City in snow"
            val title = "New York City in snow Image"
            ImageCard(painter = painters, contentDescription = description, title = title)
        }
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
    val painters = painterResource(id = R.drawable.nyc_in_snow)
    val description = "New York City in snow"
    val title = "New York City in snow Image"
    ImageCard(painter = painters, contentDescription = description, title = title)

}

// there is no margin in compose as we can do all the work with padding
// offset(): it will offset the element just like margin does it but it won't push away other elements,
// it always starts from top left corner of our composable