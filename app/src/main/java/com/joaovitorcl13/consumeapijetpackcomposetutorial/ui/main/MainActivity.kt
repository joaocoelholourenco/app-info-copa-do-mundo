package com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.joaovitorcl13.consumeapijetpackcomposetutorial.R
import com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.home.HomeScreen
import com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.home.HomeViewModel
import com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.matchdetail.MatchDetailsScreen
import com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.mymatches.MyMatchesScreen
import com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.navigation.NavigationItem
import com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CopaDoMundo2022Theme {
                val navControllerBottom = rememberNavController()
                val bottomBarVisibility = rememberSaveable { (mutableStateOf(true)) }

                Scaffold(
                    topBar = {
                        AnimatedVisibility(
                            visible = bottomBarVisibility.value,
                            enter = slideInVertically(initialOffsetY = { 0 }),
                            exit = slideOutVertically(targetOffsetY = { 0 }),
                            content = {
                                HeaderHome()
                            })

                        },
                    content = { padding ->
                        Column(modifier = Modifier.padding(padding)) {
                            Navigation(navControllerBottom,bottomBarVisibility )
                        }
                    },
                    bottomBar = {
                        AnimatedVisibility(
                            visible = bottomBarVisibility.value,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it }),
                            content = {
                                BottomNavigationBar(navControllerBottom)
                            })
                    }
                )
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navControllerBottom: NavHostController, bottomBarVisibility: MutableState<Boolean>) {
    val systemUiController = rememberSystemUiController()

    NavHost(navController = navControllerBottom, startDestination = "home", builder = {
        composable(NavigationItem.Home.route) {
            LaunchedEffect(null) {
                bottomBarVisibility.value = true
                systemUiController.setStatusBarColor(
                    color = Color.Transparent,
                    darkIcons = true
                )
            }
            val viewModel = hiltViewModel<HomeViewModel>()

            HomeScreen(viewModel, navControllerBottom )
        }
        composable(NavigationItem.MyMatches.route) {
            LaunchedEffect(null) {
                bottomBarVisibility.value = true
            }

            MyMatchesScreen()

        }
        composable("matchdetails") {
            LaunchedEffect(null) {
                bottomBarVisibility.value = false
                systemUiController.setStatusBarColor(
                    color = Color.LightGray,
                    darkIcons = true
                )
            }

            MatchDetailsScreen()
        }
    }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HeaderHome() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Red
        ),
        title = {
            Image(
                painterResource(R.drawable.ic_logoqatar), contentDescription = "Logo Catar",
            )
        },
    )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(NavigationItem.Home, NavigationItem.MyMatches)

    NavigationBar(
        containerColor = Red, contentColor = White,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
    ) {


        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { _, item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = White,
                    selectedTextColor = White,
                    unselectedIconColor = Red500,
                    unselectedTextColor = Red500,
                    indicatorColor = Gray700,

                    ),
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier
                            .size(30.dp)
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {

                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

