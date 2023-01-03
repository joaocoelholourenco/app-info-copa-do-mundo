package com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.joaovitorcl13.consumeapijetpackcomposetutorial.R
import com.joaovitorcl13.consumeapijetpackcomposetutorial.domain.item.GameItem
import com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navControllerBottom: NavHostController) {
    val games by viewModel.games.collectAsState()



    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Red,
                        Black,
                        Black,
                        Black,
                    )
                )
            ),
    ) {
        item {
            Column {
                Text(
                    modifier = Modifier.padding(20.dp, 32.dp, 0.dp, 0.dp),
                    text = "Próximos jogos",
                    color = White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(games) { match ->
                        NextMatchesCard(match, navControllerBottom)
                    }
                }
                Spacer(Modifier.height(12.dp))
            }
        }
        item {
            Button(
                onClick = {},
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Red, contentColor = White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp)
            ) {
                Text(text = "Buscar partida", color = White, fontWeight = FontWeight.Bold)
            }
        }
        item {
            Spacer(Modifier.height(24.dp))
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth().fillParentMaxHeight()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp)
            ) {
                Text(
                    text = "Resultados",
                    color = White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (games.isEmpty()) {
                        item {
                            Text(text = "Carregando...")
                        }
                    }
                    items(games) { game ->
                        Results(game, navControllerBottom)
                    }
                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Results(match: GameItem, navControllerBottom: NavHostController, ) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { navControllerBottom.navigate("matchdetails") }
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Gray800)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
        ) {
            Column(

                verticalArrangement = Arrangement.spacedBy(14.dp),

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = match.stageName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = White
                    )

                    Text(
                        text = "●",
                        fontSize = 3.sp,
                        color = Gray400
                    )

                    Text(
                        text = getFormattedDate(match.date, "dd/MM"),
                        fontSize = 12.sp,
                        color = Gray400
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = rememberAsyncImagePainter(getUrlFlag(match.homeTeam.country)),
                        contentDescription = "Team 1",
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .width(50.dp)
                            .height(37.dp),
                    )

                    Text(
                        text =match.homeTeam.goals.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = White
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_x),
                        contentDescription = "vs",
                        tint = Gray400,
                        modifier = Modifier
                            .size(24.dp)
                    )

                    Text(
                        text =match.awayTeam.goals.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = White
                    )

                    Image(
                        contentScale = ContentScale.Crop,
                        painter = rememberAsyncImagePainter(getUrlFlag(match.awayTeam.country)),
                        contentDescription = "Team 2",
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .width(50.dp)
                            .height(37.dp),
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NextMatchesCard(match: GameItem, navControllerBottom: NavHostController) {

    Box(

        modifier = Modifier
            .clickable { navControllerBottom.navigate("matchdetails") }
            .clip(RoundedCornerShape(8.dp))
            .width(280.dp)
            .height(160.dp)
            .background(Gray800)
            .paint(
                rememberAsyncImagePainter(getVenueUrlImage(match.venue)),
                contentScale = ContentScale.Crop
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0F to Color.Transparent,
                        .5F to Gray800.copy(alpha = 0.5F),
                        1F to Gray800.copy(alpha = 1F)
                    )
                )

        ) {
            Column(
                modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = rememberAsyncImagePainter(getUrlFlag(match.homeTeam.country)),
                        contentDescription = "Team 1",
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .width(50.dp)
                            .height(37.dp),
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_x),
                        contentDescription = "vs",
                        tint = White,
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = rememberAsyncImagePainter(getUrlFlag(match.awayTeam.country)),
                        contentDescription = "Team 2",
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .width(50.dp)
                            .height(37.dp),
                    )
                }
                Text(
                    text = "${match.homeTeam.name} vs ${match.awayTeam.name}",
                    fontSize = 16.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " ${getFormattedDate(match.date,"dd")} de ${getMonth(match.date)} às ${getFormattedDate(match.date, "HH:mm")}h",
                    fontSize = 12.sp,
                    color = Gray400

                )
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
fun getMonth(date: String): String {
    return when(getFormattedDate(date, "MM").toInt()) {
        1 -> "Janeiro"
        2 -> "Fevereiro"
        3 -> "Março"
        4 -> "Abril"
        5 -> "Maio"
        6 -> "Junho"
        7 -> "Julho"
        8 -> "Agosto"
        9 -> "Setembro"
        10 -> "Outubro"
        11 -> "Novembro"
        12 -> "Dezembro"
        else -> "Invalid month"
    }

}

fun getVenueUrlImage(venue: String): String {
    return when(venue) {
        "Al Bayt Stadium" -> "https://guiadoqatar.com.br/wp-content/uploads/2021/02/al-bayt-2-770x439.jpg"
        "Khalifa International Stadium" -> "https://media.gazetadopovo.com.br/amp-stories/sites/4/2022/02/11144602/estadios-futebol-copa-do-mundo-catar-2022-7.jpg"
        "Ahmad Bin Ali Stadium" -> "https://fifaworldcupmania.com/wp-content/uploads/2022/10/Facts-About-Ahmad-Bin-Ali-Stadium.jpg"
        "Al Thumama Stadium" -> "https://www.qatar2022.qa/sites/default/files/styles/1440x815/public/2022-08/Al-Thumama-Stadium.jpg?h=98540297&itok=Gu6kll24"
        "Lusail Stadium" -> "https://img.r7.com/images/lusail-stadium-catar-copa-do-mundo-2022-02082022085837017?dimensions=620x296"
        "Education City Stadium" -> "https://conteudo.imguol.com.br/c/esporte/31/2022/09/02/estadio-education-city-um-dos-oito-estadios-da-copa-do-mundo-do-qatar-1662160556575_v2_600x450.jpg"
        "Stadium 974" -> "https://visitqatar.com/content/dam/visitqatar/img/things-to-do/adventures/other-sports-and-activities/new-stadium-pictures/974_stadium_4_11zon.jpg/_jcr_content/renditions/medium-1280px.jpeg"
        "Al Janoub Stadium" -> "https://lines-hub.com/wp-content/uploads/2019/05/Photo1-1-1170x780.jpg"

        else -> "https://media.gazetadopovo.com.br/amp-stories/sites/4/2022/02/11144602/estadios-futebol-copa-do-mundo-catar-2022-7.jpg"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedDate(date: String, format: String): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val outputFormatter = SimpleDateFormat(format)
    val dateParse: Date = inputFormatter.parse(date)
    return outputFormatter.format(dateParse)
}

fun getUrlFlag(country: String): String {
    val flagCode = when(country) {
        "ENG" -> "GB-ENG"
        "NED" -> "NLD"
        "WAL" -> "GB-WLS"
        "KSA" -> "SAU"
        "DEN" -> "DNK"
        "CRO" -> "HRV"
        "GER" -> "DEU"
        "CRC" -> "CRI"
        "SUI" -> "CHE"
        "URU" -> "URY"
        "POR" -> "PRT"
        else -> country
    }

    return "https://countryflagsapi.com/png/$flagCode"

}

