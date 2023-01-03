package com.joaovitorcl13.consumeapijetpackcomposetutorial.domain

import com.joaovitorcl13.consumeapijetpackcomposetutorial.domain.item.GameItem
import com.joaovitorcl13.consumeapijetpackcomposetutorial.repo.GameRepository
import javax.inject.Inject


class GetGamesUseCase @Inject constructor(private val gameRepository: GameRepository) {

    suspend operator fun invoke(): List<GameItem> {

        return gameRepository.getGames()

    }

}