package edu.training.myapplication.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import edu.training.myapplication.presentation.news_navigator.NewsNavigator
import edu.training.myapplication.presentation.onboarding.OnBoardingScreen
import edu.training.myapplication.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    
    NavHost(navController = navController, startDestination = startDestination){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(
                route = Route.OnBoardingScreen.route
            ){
                val viewModel : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = {
                    viewModel.onEvent(it)
                })
            }
        }

        navigation(route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route){
            composable(route = Route.NewsNavigationScreen.route){
                NewsNavigator()
            }
        }
    }
}
