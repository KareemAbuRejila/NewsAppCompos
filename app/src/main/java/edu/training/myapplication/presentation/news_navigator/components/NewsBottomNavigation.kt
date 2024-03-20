package edu.training.myapplication.presentation.news_navigator.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.training.myapplication.R
import edu.training.myapplication.presentation.Dimens.ExtraSmallPadding2
import edu.training.myapplication.presentation.Dimens.IconSize
import edu.training.myapplication.ui.theme.NewsAppComposTheme

@Composable
fun NewsBottomNavigation(
  items: List<BottomNavigationItem>,
  selected: Int,
  onItemClick: (Int) -> Unit
){
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index)},
                icon = {
                    Column (horizontalAlignment = CenterHorizontally) {
                        Icon(
                            modifier = Modifier.height(IconSize),
                            painter = painterResource(id = item.icon),
                            contentDescription = "Item ${item.text} Icon"
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }

    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview(){
    NewsAppComposTheme {
        NewsBottomNavigation(
            listOf(BottomNavigationItem(R.drawable.ic_home,"Home"),
                BottomNavigationItem(R.drawable.ic_search,"Search"),
                BottomNavigationItem(R.drawable.ic_bookmark,"BookMark"),
                ),
            0
        ) {}
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon : Int,
    val text: String
)