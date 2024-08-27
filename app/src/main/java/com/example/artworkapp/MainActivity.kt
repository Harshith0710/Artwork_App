package com.example.artworkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.artworkapp.ui.theme.ArtworkAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtworkAppTheme {
                ArtworkApp(modifier = Modifier
                    .fillMaxSize()
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ArtworkPreview(){
    ArtworkApp(modifier = Modifier
        .fillMaxSize()
    )
}

@Composable
fun ArtworkApp(modifier: Modifier){
    ArtworkWall(modifier = modifier)
}

@Composable
fun ArtworkWall(modifier: Modifier){
    var artState by rememberSaveable {
        mutableIntStateOf(1)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(artState){
            1 -> Art(modifier = Modifier,
                artImage = R.drawable.art1,
                artContent = R.string.art1
            )
            2 -> Art(modifier = Modifier,
                artImage = R.drawable.art2,
                artContent = R.string.art2
            )
            3 -> Art(modifier = Modifier,
                artImage = R.drawable.art3,
                artContent = R.string.art3
            )
            4 -> Art(modifier = Modifier,
                artImage = R.drawable.art4,
                artContent = R.string.art4
            )
        }
        when(artState){
            1 -> ArtDetails(modifier = Modifier,
                artName = R.string.art1,
                artist = R.string.artist1,
                artYear = R.string.year1
                )
            2 -> ArtDetails(modifier = Modifier,
                artName = R.string.art2,
                artist = R.string.artist2,
                artYear = R.string.year2
            )
            3 -> ArtDetails(modifier = Modifier,
                artName = R.string.art3,
                artist = R.string.artist3,
                artYear = R.string.year3
            )
            4 -> ArtDetails(modifier = Modifier,
                artName = R.string.art4,
                artist = R.string.artist4,
                artYear = R.string.year4
            )
        }
        ScreenChanger(modifier = Modifier,
            clickNext = {
                if(artState < 4) artState++
            },
            clickPrevious = {
                if(artState > 1) artState--
            }
            )
    }
}

@Composable
fun Art(modifier: Modifier, artImage: Int, artContent: Int) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shadowElevation = 10.dp
        ) {
            BoxWithConstraints(modifier = modifier) {
                val imageSize = if (maxWidth < 600.dp) {
                    Modifier.size(300.dp)
                } else {
                    Modifier.size(400.dp)
                }

                Image(
                    painter = painterResource(id = artImage),
                    contentDescription = stringResource(id = artContent),
                    modifier = imageSize
                        .padding(horizontal = 30.dp)
                )
            }
        }
    }
}

@Composable
fun ArtDetails(modifier: Modifier, artName: Int, artist: Int, artYear: Int){
    Column(
        modifier = modifier
            .padding(30.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .width(450.dp)
    ) {
        Text(text = stringResource(id = artName),
            fontSize = 8.em,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            lineHeight = 40.sp,
            modifier = Modifier
                .padding(start = 25.dp, top = 15.dp, end = 15.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row {
            Text(text = stringResource(id = artist),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 25.dp, bottom = 15.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = stringResource(id = artYear),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .padding(end = 25.dp)
            )
        }
    }
}

@Composable
fun ScreenChanger(modifier: Modifier, clickNext: () -> Unit, clickPrevious: () -> Unit){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 25.dp)
    ) {
        Button(onClick = clickPrevious) {
            Text(text = stringResource(id = R.string.previous)
                )
        }
        Button(onClick = clickNext) {
            Text(text = stringResource(id = R.string.next)
            )
        }
    }
}