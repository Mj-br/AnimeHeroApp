package edu.robertconstantin.animeappcliient.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import edu.robertconstantin.animeappcliient.core.presentation.navigation.BottomNavMenu
import edu.robertconstantin.animeappcliient.core.presentation.navigation.screen.BottomMenuScreen
import edu.robertconstantin.animeappcliient.core.ui.theme.AnimeAppCliientTheme
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_create_screen.HeroCreateScreen
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_favorites_screen.HeroFavoritesScreen
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.common.HeroBaseScreen
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_feed_screen.HeroFeedScreen
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var imageLoader: ImageLoader

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // Punto de entrada de nuestros elementos componibles
            AnimeAppCliientTheme {// Tema de la app

                //controlador de navegacion
                val navController = rememberNavController()
                //propiedad que determina en toodo momento que pantalla esta en nuestra pila
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                //propiedad que constituye el andamio sobre el que se va a montar los
                //diferentes elementos que conforman nuestra app. Por ejemplo la barra de navegacion
                val scaffoldState = rememberScaffoldState()
                //variable que contiene el contexto de la aplicacion.
                val context = LocalContext.current

                //Este es el contenedor principal de la app y tiene nuestro color de fondo.
                Surface( // Elemento componible que representa la superficie de una ventana o de un contenedor
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Nuestro andamio que conformara la pantalla general,  tendra una serie de elementos.
                    androidx.compose.material.Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState,
                        bottomBar = {
                            BottomNavMenu(
                                navController = navController,
                                //Implementacion de la lambda de navegacion.
                                onBottomIconClick = { route ->
                                    navController.navigate(route) {
                                        navController.graph.startDestinationRoute?.let { route ->
//                                            popUpTo(route) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                }
                            )

                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = BottomMenuScreen.HeroFeedScreen.route) {

                            composable(route = BottomMenuScreen.HeroFeedScreen.route) {
                                HeroFeedScreen(imageLoader = imageLoader, showUserInfo = {
                                    lifecycleScope.launchWhenStarted {
                                        scaffoldState.snackbarHostState.showSnackbar(message = it)
                                    }
                                })
                            }

                            composable(route = BottomMenuScreen.HeroFavoritesScreen.route) {
                                HeroFavoritesScreen(imageLoader = imageLoader, showUserInfo = {
                                    lifecycleScope.launchWhenStarted {
                                        scaffoldState.snackbarHostState.showSnackbar(message = it)
                                    }
                                })
                            }

                            composable(route = BottomMenuScreen.HeroCreateScreen.route) {
                                HeroCreateScreen()
                            }

                        }
                    }
                }
            }
        }
    }
}