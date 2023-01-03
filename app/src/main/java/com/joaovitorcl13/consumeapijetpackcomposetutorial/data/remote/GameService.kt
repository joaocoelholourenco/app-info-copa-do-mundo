package com.joaovitorcl13.consumeapijetpackcomposetutorial.data.remote


import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.joaovitorcl13.consumeapijetpackcomposetutorial.data.remote.model.GameModel
import com.joaovitorcl13.consumeapijetpackcomposetutorial.domain.item.GameItem
import com.joaovitorcl13.consumeapijetpackcomposetutorial.domain.item.toGameItem

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class GameService @Inject constructor(private val gameApi: GameApi) {

    suspend fun getGames(): List<GameModel> {

        return withContext(Dispatchers.IO) {
            val games = gameApi.getGames()
            games.body() ?: emptyList()
        }

    }
}