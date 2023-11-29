package com.example.lemonade

import android.graphics.drawable.shapes.ArcShape
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeSteps(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var squeezeClickCount by remember { mutableStateOf(0) }
    var random by remember { mutableStateOf((0..2).random()) }

    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_restart
    }

    val textContextDescrp = when (result) {
        1 -> stringResource(R.string.lemon_tree_content_description)
        2 -> stringResource(R.string.lemon_content_description)
        3 -> stringResource(R.string.glass_of_lemonade_content_description)
        4 -> stringResource(R.string.empty_glass_content_description)
        else -> stringResource(R.string.empty_glass_content_description)
    }

    Column(
        modifier = Modifier
            .background(color = Color.Yellow)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.Title),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                top = 20.dp,
                bottom = 20.dp
            )
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = textContextDescrp, // Utiliza textContextDescrp aquí
            modifier = Modifier
                .padding(all = 16.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(20.dp))
                .scale(0.8f)
                .size(250.dp)
                .clickable {
                    when (result) {
                        1 -> result = 2
                        2 -> {
                            squeezeClickCount++
                            if (squeezeClickCount == (2 + random)) {
                                result = 3
                                squeezeClickCount = 0
                            }
                        }
                        3 -> result = 4
                        4 -> {
                            result = 1
                            random = (0..2).random()
                        }
                    }
                }
        )

        Text(
            text = when (result) {
                1 -> stringResource(R.string.lemon_tree)
                2 -> stringResource(R.string.tap_lemon)
                3 -> stringResource(R.string.tap_lemonade)
                4 -> stringResource(R.string.tap_empty_glass)
                else -> stringResource(R.string.tap_empty_glass)
            },
            modifier = Modifier.padding(
                top = 20.dp,
                bottom = 20.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeSteps(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}
