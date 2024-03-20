package edu.training.myapplication.presentation.news_navigator

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import edu.training.myapplication.R
import edu.training.myapplication.domain.model.Article
import edu.training.myapplication.presentation.bookmark.BookMarkViewModel
import edu.training.myapplication.presentation.bookmark.BookmarkScreen
import edu.training.myapplication.presentation.deatils.DetailsScreen
import edu.training.myapplication.presentation.deatils.DetailsViewModel
import edu.training.myapplication.presentation.home.HomeScreen
import edu.training.myapplication.presentation.home.HomeViewModel
import edu.training.myapplication.presentation.navgraph.Route
import edu.training.myapplication.presentation.news_navigator.components.BottomNavigationItem
import edu.training.myapplication.presentation.news_navigator.components.NewsBottomNavigation
import edu.training.myapplication.presentation.search.SearchScreen
import edu.training.myapplication.presentation.search.SearchViewModel
import edu.training.myapplication.ui.theme.NewsAppComposTheme

@Composable
fun NewsNavigator(){

    val bottomNavigationItems = remember{
        listOf(
            BottomNavigationItem(R.drawable.ic_home,"Home"),
            BottomNavigationItem(R.drawable.ic_search,"Search"),
            BottomNavigationItem(R.drawable.ic_bookmark,"BookMark"),
        )
    }
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when(backStackState?.destination?.route){
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selected =0,
                onItemClick ={
                    when(it){
                        0 -> navigateToTap(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )
                        1 -> navigateToTap(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                        2 -> navigateToTap(
                            navController = navController,
                            route = Route.BookmarkScreen.route
                        )
                    }
                }
            )
        }
    ) { it ->
        val bottomPadding = it.calculateBottomPadding()
        NavHost(modifier = Modifier.padding(bottom = bottomPadding),
            navController = navController,
            startDestination = Route.HomeScreen.route){
            composable(route = Route.HomeScreen.route){
                val viewModel: HomeViewModel = hiltViewModel()
                val artists = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = artists,
                    navigateToSearch = {
                                       navigateToTap(navController = navController, route = Route.SearchScreen.route)
                    },
                    navigateToDetails = {article->
                        navigateToDetailsScreen(
                            navController,
                            article
                        )
                    }
                )
            }

            composable(route = Route.SearchScreen.route){
                val searchViewModel : SearchViewModel = hiltViewModel()
                SearchScreen(
                    state = searchViewModel.state.value,
                    event = searchViewModel::onEvent,
                    navigateToDetailsScreen = {article ->
                        navigateToDetailsScreen(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Route.DetailsScreen.route){
                val viewModel : DetailsViewModel = hiltViewModel()
                //TODO: Handle side Effect
                navController
                    .previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Article>("article")
                    ?.let {article->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = {navController.navigateUp()}
                        )
                    }
            }

            composable(route = Route.BookmarkScreen.route){
                val viewModel : BookMarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    state = state,
                    navigateToDetailsScreen = { article ->
                        navigateToDetailsScreen(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
        }
    }

}

fun navigateToDetailsScreen(navController: NavHostController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}


fun navigateToTap(navController: NavController, route:String){
    navController.navigate(route = route){
        navController.graph.startDestinationRoute?.let {homeScreen->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsNavigatorPreview(){
    NewsAppComposTheme {
        NewsNavigator()
    }
}