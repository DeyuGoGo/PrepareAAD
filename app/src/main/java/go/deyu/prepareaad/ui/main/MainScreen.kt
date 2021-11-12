package go.deyu.prepareaad.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.intersense.myneighbor.navi.NavigationDirections
import go.deyu.prepareaad.MainActivity
import go.deyu.prepareaad.ui.component.ComponentRootScreen
import go.deyu.prepareaad.ui.component.dialog.TestDialogScreen
import go.deyu.prepareaad.ui.component.location.ShowLocationScreen
import go.deyu.prepareaad.ui.loading.TestLoadingScreen

@ExperimentalPermissionsApi
@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    val selectedTab by viewModel.selectedTab.observeAsState(MainActivity.MainTabs.BUTTON_PAGE)
    Scaffold(
        bottomBar = {
            BottomNavigation(
                Modifier.navigationBarsHeight(additional = 56.dp)
            ) {
                MainActivity.MainTabs.values().forEach { tab ->
                    BottomNavigationItem(
                        icon = {
                            Icon(tab.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = tab.title.uppercase())
                        },
                        selected = tab == selectedTab,
                        onClick = {
                            viewModel.navigate(tab.route)
                        },
                        alwaysShowLabel = false,
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = LocalContentColor.current,
                        modifier = Modifier
                            .navigationBarsPadding()
                            .clip(RoundedCornerShape(20.dp))
                    )
                }
            }
        }
    ) {
        MainNavHost(navController = navController, modifier = Modifier.padding(it))
    }
}

@ExperimentalPermissionsApi
@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = NavigationDirections.buttons_main.destination,
        modifier = modifier
    )
    {
        navigation(
            startDestination = NavigationDirections.buttons_root.destination,
            route = NavigationDirections.buttons_main.destination
        ) {
            composable(route = NavigationDirections.buttons_root.destination) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(onClick = {
                        //沒幹麼
                    }) {
                        Text("按鈕")
                    }
                }
            }
        }


        navigation(
            startDestination = NavigationDirections.animation_root.destination,
            route = NavigationDirections.animation_main.destination
        ) {
            composable(route = NavigationDirections.animation_root.destination) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    TestLoadingScreen()
                }
            }
        }



        navigation(
            startDestination = NavigationDirections.component_root.destination,
            route = NavigationDirections.component_main.destination
        ) {
            composable(route = NavigationDirections.component_root.destination) {
                ComponentRootScreen(viewModel = hiltViewModel())
            }
            composable(route = NavigationDirections.component_location.destination) {
                ShowLocationScreen(viewModel = hiltViewModel())
            }
            composable(route = NavigationDirections.component_dialog.destination) {
                TestDialogScreen()
            }
        }

    }
}