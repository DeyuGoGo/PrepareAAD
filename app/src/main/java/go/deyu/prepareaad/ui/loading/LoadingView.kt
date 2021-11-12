package go.deyu.prepareaad.ui.loading

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import go.deyu.prepareaad.R
import kotlinx.coroutines.delay


@Composable
fun TestLoadingScreen() {
    val (isShow, onShowChange) = rememberSaveable { mutableStateOf(true) }
    if (isShow) {
        LaunchedEffect(key1 =  null, block = {
            delay(3000)
            onShowChange(false)
        })
        LoadingView()
    } else {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {onShowChange(true)}) {
                Text(text = "Show Loading")
            }
        }
    }

}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            val infiniteTransition = rememberInfiniteTransition()
            val rotation by infiniteTransition.animateFloat(
                initialValue = -45f,
                targetValue = 45f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1000,
                        easing = FastOutLinearInEasing,

                        ),
                    repeatMode = RepeatMode.Reverse
                )
            )

            val size by infiniteTransition.animateValue(
                initialValue = 200.dp,
                targetValue = 300.dp,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 300,
                        easing = FastOutLinearInEasing,

                        ),
                    repeatMode = RepeatMode.Reverse
                ),
                typeConverter = Dp.VectorConverter,
            )

            Image(
                painter = rememberCoilPainter(request = R.drawable.c_melon),
                contentDescription = "Bird",
                modifier = Modifier
                    .rotate(rotation)
                    .size(size)
            )
        })
}


@Preview(showBackground = true)
@Composable
fun previewLoading() {
    LoadingView()
}