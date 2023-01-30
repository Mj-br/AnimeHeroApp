package edu.robertconstantin.animeappcliient.core.presentation.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.robertconstantin.animeappcliient.core.presentation.navigation.screen.BottomMenuScreen

//Este elemento componible
@Composable
fun BottomNavMenu(
    navController: NavController,
    onBottomIconClick: (route: String) -> Unit = {},
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.background
) {

    //lista de elementos para ser mostrados. Contiene el icono, titulo y la ruta.
    val menuItems = listOf(
        BottomMenuScreen.HeroFeedScreen,
        BottomMenuScreen.HeroFavoritesScreen,
        BottomMenuScreen.HeroCreateScreen
    )

    //Creacion de la barra de navegacion.
    BottomNavigation(
        modifier = Modifier
            //usamos esta funcion para darle un aspecto determinado
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                shadowElevation = 2.5f
            },
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colorScheme.surface,
    ){
        //Pagina actual del stack
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        //Ruta actual
        val currentRoute = navBackStackEntry?.destination?.route
        //Nos recoremos cada uno de los items del menu y por cadaa uno de ellos vamos a crear
        //un BottomNavigationItem/
        menuItems.forEach { bottomMenuItem ->
            BottomNavigationItem(
                //estara selecionado cuando la ruta actual que hay en la pila sea igual a la del propio elemento.
                selected = currentRoute == bottomMenuItem.route,
                //llamamos a la lambda para hacer la navegacion y pasamos la ruta para dirigirnos a ella
                //en el lugar de la implementacion de esta lambda.
                onClick = { onBottomIconClick(bottomMenuItem.route) }, //Lambda que se ejecuta cuando se hace click en el elemento
                label = {
                    Text(
                        text = bottomMenuItem.title,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 10.sp
                    )
                },
                alwaysShowLabel = false,
                icon = {
                    bottomMenuItem.icon?.let {
                        Icon(
                            tint = if (currentRoute == bottomMenuItem.route) selectedColor else unselectedColor,
                            imageVector = bottomMenuItem.icon,
                            contentDescription = bottomMenuItem.title
                        )
                    }
                }
            )
        }

    }


}