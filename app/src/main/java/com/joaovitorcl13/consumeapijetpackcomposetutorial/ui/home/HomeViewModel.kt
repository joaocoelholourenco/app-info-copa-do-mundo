package com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaovitorcl13.consumeapijetpackcomposetutorial.domain.GetGamesUseCase
import com.joaovitorcl13.consumeapijetpackcomposetutorial.domain.item.GameItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getGamesUseCase: GetGamesUseCase) : ViewModel() {

    private val _games = MutableStateFlow(emptyList<GameItem>())
    val games: StateFlow<List<GameItem>> get() = _games

    init {
        getGames()
    }

    private fun getGames() {

        viewModelScope.launch {


                val games = getGamesUseCase()
                _games.value = games
        }

    }

}