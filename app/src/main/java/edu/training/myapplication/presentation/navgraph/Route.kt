package edu.training.myapplication.presentation.navgraph

sealed class Route(
    val route: String
) {

    object AppStartNavigation : Route(route = "appStartNavigation")
    object OnBoardingScreen : Route(route = "OnBoardingScreen")

    object HomeScreen : Route(route = "HomeScreen")
    object SearchScreen : Route(route = "SearchScreen")
    object BookmarkScreen : Route(route = "BookmarkScreen")
    object DetailsNavigation : Route(route = "DetailsNavigation")
    object DetailsScreen : Route(route = "DetailsScreen")
    object NewsNavigation : Route(route = "NewsNavigation")
    object NewsNavigationScreen : Route(route = "NewsNavigationScreen")

}