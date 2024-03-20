package edu.training.myapplication.presentation.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import edu.training.myapplication.data.local.NewsDao
import edu.training.myapplication.domain.model.Article
import edu.training.myapplication.domain.model.Source
import edu.training.myapplication.presentation.common.EmptyErrScreen
import edu.training.myapplication.presentation.navgraph.NavGraph
import edu.training.myapplication.ui.theme.NewsAppComposTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    @Inject
    lateinit var dao: NewsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        lifecycleScope.launch {
            dao.upsert(
                Article(
                    author = "Emily Shapiro",
                    title = "Several shot after Chiefs Super Bowl parade in Kansas City - ABC News",
                    description = "Two armed people have been detained, Kansas City police said.",
                    url = "https://abcnews.go.com/US/shooting-reported-kansas-city-after-chiefs-super-bowl/story?id=107238682",
                    urlToImage = "https://i.abcnewsfe.com/a/7edb6c4c-9969-4047-9272-83c04ae8bd71/chiefs-rally-shooting-gty-jt-240214_1707941812285_hpMain_16x9.jpg?w=1600",
                    publishedAt = "2024-02-14T20:15:00Z",
                    content = "One person is dead and nine are injured from a shooting in Kansas City, Missouri, following the parade and rally for the Chiefs' Super Bowl win, according to the Kansas City Fire Department.\r\nThree v… [+1590 chars]",
                    source = Source("abc-news","ABC News")
                )
            )
        }


        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition.value
            }
        }
        setContent {
            NewsAppComposTheme(dynamicColor = false) {
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }

                Box(modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()) {
                    NavGraph(startDestination = viewModel.startDestination.value)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAppComposTheme {
        MainActivity()
    }
}