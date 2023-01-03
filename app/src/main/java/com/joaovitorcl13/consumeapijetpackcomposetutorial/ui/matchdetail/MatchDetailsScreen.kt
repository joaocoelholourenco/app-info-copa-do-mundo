package com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.matchdetail


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.rememberAsyncImagePainter
import com.joaovitorcl13.consumeapijetpackcomposetutorial.R

import com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.theme.*


@Composable
fun MatchDetailsScreen() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Black)
                .height(216.dp)
                .paint(
                    rememberAsyncImagePainter("https://guiadoqatar.com.br/wp-content/uploads/2021/02/al-bayt-2-770x439.jpg"),
                    contentScale = ContentScale.Crop
                )
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            0F to Color.Transparent,
                            .5F to Color.Transparent,
                            1F to Black
                        )
                    )

            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        5.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(12.dp, 0.dp, 0.dp, 12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            12.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            contentScale = ContentScale.Crop,
                            painter = rememberAsyncImagePainter("https://countryflagsapi.com/png/QAT"),
                            contentDescription = "Team 1",
                            modifier = Modifier
                                .width(50.dp)
                                .height(37.dp),
                        )
                        Text(
                            text = "0",
                            color = White,
                            fontSize = 18.sp
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_x),
                            contentDescription = "vs",
                            tint = White,
                            modifier = Modifier
                                .size(16.dp)
                        )
                        Text(
                            text = "2",
                            color = White,
                            fontSize = 18.sp
                        )
                        Image(
                            contentScale = ContentScale.Crop,
                            painter = rememberAsyncImagePainter("https://countryflagsapi.com/png/ECU"),
                            contentDescription = "Team 2",
                            modifier = Modifier
                                .width(50.dp)
                                .height(37.dp),
                        )
                    }


                }
            }
        }
        Column(
            modifier = Modifier.background(Black)

        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(
                    3.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()

            ) {
                Text(
                    text = "Qatar vs Ecuador",
                    color = White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Khalifa International Stadium",
                    color = Gray400,
                    fontSize = 12.sp,
                )
                Text(
                    text = "Fase de Grupo - Grupo A",
                    color = White,
                    fontSize = 12.sp,
                )
                Text(
                    text = "20 de Nov, 16:00",
                    color = White,
                    fontSize = 14.sp,
                )
            }
        }

    }
}