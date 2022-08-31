package com.seif.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seif.learningcompose.ui.theme.LearningComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // has all the composables that it should draw
            Column(
                modifier = Modifier
                    .background(Color.Green)
                    .padding(top = 40.dp)
                    .width(200.dp)
                    .height(300.dp),
                //  horizontalAlignment = Alignment.CenterHorizontally,
                //  verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hello", modifier =
                    Modifier
                        .border(5.dp, Color.Black)
                        .offset(20.dp, 20.dp)
                        .border(5.dp, Color.White)
                )
                Spacer(modifier = Modifier.height(50.dp)) // will insert an empty composable that has hieght of 50dp
                Text(text = "Seif")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() { // we can preview our composable
    Greeting("Engineer Seif")
}

// there is no margin in compose as we can do all the work with padding
// offset(): it will offset the element just like margin does it but it won't push away other elements,
// it always starts from top left corner of our composable