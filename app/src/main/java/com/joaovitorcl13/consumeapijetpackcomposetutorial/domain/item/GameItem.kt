package com.joaovitorcl13.consumeapijetpackcomposetutorial.domain.item

import com.joaovitorcl13.consumeapijetpackcomposetutorial.data.remote.model.AwayTeam
import com.joaovitorcl13.consumeapijetpackcomposetutorial.data.remote.model.GameModel
import com.joaovitorcl13.consumeapijetpackcomposetutorial.data.remote.model.HomeTeam

data class GameItem(
    val id: String,
    val homeTeam: HomeTeam,
    val date: String,

    val stageName: String,

    val awayTeam: AwayTeam,
    val venue: String,

    )

fun GameModel.toGameItem() = GameItem(id, homeTeam, date, stageName, awayTeam, venue)
