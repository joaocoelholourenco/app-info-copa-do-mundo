package com.joaovitorcl13.consumeapijetpackcomposetutorial.data.remote

import com.joaovitorcl13.consumeapijetpackcomposetutorial.data.remote.model.GameModel
import com.joaovitorcl13.consumeapijetpackcomposetutorial.util.Constants.Companion.END_POINT_MATCH
import com.joaovitorcl13.consumeapijetpackcomposetutorial.util.Constants.Companion.END_POINT_MATCHES
import retrofit2.Call

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GameApi {

    @GET(END_POINT_MATCHES)
    suspend fun getGames(): Response<List<GameModel>>
}