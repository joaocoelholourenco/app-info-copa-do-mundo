package com.joaovitorcl13.consumeapijetpackcomposetutorial.repo

import androidx.lifecycle.MutableLiveData
import com.joaovitorcl13.consumeapijetpackcomposetutorial.data.remote.GameService
import com.joaovitorcl13.consumeapijetpackcomposetutorial.data.remote.model.GameModel
import com.joaovitorcl13.consumeapijetpackcomposetutorial.domain.item.GameItem
import com.joaovitorcl13.consumeapijetpackcomposetutorial.domain.item.toGameItem
import javax.inject.Inject

class GameRepository @Inject constructor(private val gameService: GameService) {

    suspend fun getGames(): List<GameItem> {

        return gameService.getGames().map {
            it.toGameItem()
        }

    }
}