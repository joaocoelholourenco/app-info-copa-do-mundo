package com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.mymatches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.theme.Black
import com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.theme.Red
import com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.theme.White

@Composable
fun MyMatchesScreen() {
    val list = false
    val sweepstakes =
        listOf(
            "Bolão do Joãozinho",
            "Turma do trabalho",
            "Se leu mamou",
            "Diretoria",
            "Arg x Ger",
            "Jap x Ira"
        )

    LazyColumn(
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
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

        if (list) {
            item {

                Text(
                    text = "Nenhuma partida salva :(",
                    color = White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        items(sweepstakes) { sweepstake ->
           // Results(sweepstake, navController)
        }
    }




}
