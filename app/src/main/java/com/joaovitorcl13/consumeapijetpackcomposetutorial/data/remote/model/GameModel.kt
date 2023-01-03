package com.joaovitorcl13.consumeapijetpackcomposetutorial.data.remote.model

import com.joaovitorcl13.consumeapijetpackcomposetutorial.domain.item.GameItem


data class GameModel(

    val id: String,
    val winner: String,
    val homeTeam: HomeTeam,
    val date: String,

    val stageName: String,

    val awayTeam: AwayTeam,
    val venue: String,
    )