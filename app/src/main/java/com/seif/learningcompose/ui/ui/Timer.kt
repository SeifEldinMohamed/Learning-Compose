package com.seif.learningcompose.ui.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    textColor: Color = Color.Black,
    radius: Dp = 50.dp,
    progressColor: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    startAngle: Float = 270f
) {
    val currentPercentage =
        remember { Animatable(0f) } // This Animatable function creates a float value holder that automatically animates its value when the value is changed via animateTo
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
                color = progressColor,
                startAngle = startAngle,
                sweepAngle = 360 * currentPercentage.value, // the animation angle
                useCenter = false, // it won't connect center lines with the center (if true then we will have a pie)
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round // caps: describes the end of our stroke to make them round
                )
            )
        }
        Text(
            text = (currentPercentage.value * number).toInt().toString(),
            color = textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}