package com.ntech.weedwhiz.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ntech.theyardhub.feature.auth.ConfigViewModel
import com.ntech.weedwhiz.core.RouteName
import com.ntech.weedwhiz.core.RouteName.CONFIG_SCREEN
import com.ntech.weedwhiz.core.RouteName.HISTORY_SCREEN
import com.ntech.weedwhiz.core.RouteName.HOME_SCREEN
import com.ntech.weedwhiz.core.RouteName.LOGIN_SCREEN
import com.ntech.weedwhiz.core.RouteName.MONITORING_SCREEN
import com.ntech.weedwhiz.core.RouteName.SPLASH_SCREEN
import com.ntech.weedwhiz.core.theme.WeedWhizTheme
import com.ntech.weedwhiz.core.theme.White
import com.ntech.weedwhiz.feature.auth.LoginScreen
import com.ntech.weedwhiz.feature.bottomnavigation.BottomNavItem
import com.ntech.weedwhiz.feature.bottomnavigation.BottomNavigationMenu
import com.ntech.weedwhiz.feature.config.ConfigScreen
import com.ntech.weedwhiz.feature.history.HistoryScreen
import com.ntech.weedwhiz.feature.home.HomeScreen
import com.ntech.weedwhiz.feature.monitoring.MonitoringScreen
import com.ntech.weedwhiz.feature.splash.SplashScreen
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: MainActivityViewModel = get()

            WeedWhizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    Scaffold(
                        Modifier.fillMaxSize(),
                        containerColor = White,
                        bottomBar = {

                        },
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            NavHost(
                                navController = navController,
                                startDestination = SPLASH_SCREEN
                            ) {
                                composable(LOGIN_SCREEN) {
                                    LoginScreen(navController)
                                }
                                composable(HOME_SCREEN) {
                                    HomeScreen(navController)
                                }
                                composable(CONFIG_SCREEN) {
                                    ConfigScreen(navController)
                                }
                                composable(HISTORY_SCREEN) {
                                    HistoryScreen(navController)
                                }
                                composable(SPLASH_SCREEN) {
                                    SplashScreen(navController)
                                }
                                composable(MONITORING_SCREEN) {
                                    MonitoringScreen(navController)
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            if (currentRoute != SPLASH_SCREEN) {
                                BottomNavigationMenu(
                                    navController = navController,
                                    selectedMenuState = viewModel.selectedMenuState.value,
                                    onItemClicked = { viewModel.setSelectedMenu(it) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeedWhizTheme {
        Greeting("Android")
    }
}