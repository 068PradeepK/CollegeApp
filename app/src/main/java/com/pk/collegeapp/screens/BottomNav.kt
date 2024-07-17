package com.pk.collegeapp.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pk.collegeapp.R
import com.pk.collegeapp.models.BottomNavItems
import com.pk.collegeapp.models.NavItems
import com.pk.collegeapp.navigation.Screens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(navController: NavHostController) {
    val navController1 = rememberNavController()
    //Text(text = "BottomNav")
    val context = LocalContext.current
    //For changing the state of drawer we need CoroutineScope so we don't block main thread
    val scope = rememberCoroutineScope()
    //Required to pass this to modal drawer
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val list = listOf(
        NavItems("Website", R.drawable.world),
        NavItems("Notice", R.drawable.notice),
        NavItems("Notes", R.drawable.notes),
        NavItems("Contact Us", R.drawable.mail)
    )
    //Setting Up navigation drawer
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet {
            Image(
                painter = painterResource(id = R.drawable.prof_img1),
                contentDescription = null,
                modifier = Modifier.height(220.dp),
                contentScale = ContentScale.Crop
            )
            HorizontalDivider()
            list.forEachIndexed { index, item ->
                NavigationDrawerItem(
                    label = { Text(text = item.title) },
                    selected = selectedItemIndex == index,
                    onClick = {
                        Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = item.icon),
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }, content = {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "College App"
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Rounded.Menu,
                                contentDescription = null,
                            )
                        }

                    }
                )
            },
            bottomBar = {
                BottomBar(navController = navController1)
            }
        ) { padding ->
            NavHost(
                navController = navController1,
                startDestination = Screens.Home.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(Screens.Home.route) {
                    Home()
                }
                composable(Screens.About.route) {
                    About()
                }
                composable(Screens.Faculty.route) {
                    Faculty()
                }
                composable(Screens.Gallery.route) {
                    Gallery()
                }
            }
        }
    })
}

@Composable
fun BottomBar(navController: NavHostController) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val list = listOf(
        BottomNavItems("Home", R.drawable.home, route = Screens.Home.route),
        BottomNavItems("Faculty", R.drawable.graduate, route = Screens.Faculty.route),
        BottomNavItems("Gallery", R.drawable.gallary, route = Screens.Gallery.route),
        BottomNavItems("About", R.drawable.info, route = Screens.About.route),
    )
    BottomAppBar(modifier = Modifier.height(72.dp)) {
        list.forEach { item ->
            val curRoute = item.route
            val otherRoute =
                try {
                    backStackEntry.value!!.destination.route
                } catch (e: Exception) {
                    Screens.Home.route
                }
            val selected = curRoute == otherRoute
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                })
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShowPrev() {
    BottomNav(navController = rememberNavController())
}