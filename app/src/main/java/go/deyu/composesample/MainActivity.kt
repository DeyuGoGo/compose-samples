package go.deyu.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import go.deyu.composesample.ui.loading.LoadingView
import go.deyu.composesample.ui.loading.TestLoadingScreen
import go.deyu.composesample.ui.theme.ComposeSampleTheme

class MainActivity : ComponentActivity() {

    enum class MainTabs(
        val title: String,
        val icon: ImageVector,
        val route: String
    ) {
        BUTTON_PAGE("按鈕", icon = Icons.Default.SmartButton, "buttons"),
        ANIMATION_PAGE("動畫", Icons.Default.Animation, "animation"),
        COMPONENT_PAGE("元件", Icons.Default.ViewCompact, "component")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    var selectedTab by mutableStateOf(MainTabs.BUTTON_PAGE)
                    Scaffold(
                        bottomBar = {
                            BottomNavigation(
                                Modifier.navigationBarsHeight(additional = 56.dp)
                            ) {
                                MainTabs.values().forEach { tab ->
                                    BottomNavigationItem(
                                        icon = {
                                            Icon(tab.icon, contentDescription = null)
                                        },
                                        label = {
                                            Text(text = tab.title.uppercase())
                                        },
                                        selected = tab == selectedTab,
                                        onClick = {
                                            navController.navigate(tab.route)
                                            selectedTab = tab
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
            }
        }
    }

    @Composable
    fun MainNavHost(navController: NavHostController, modifier: Modifier) {
        NavHost(
            navController = navController,
            startDestination = MainTabs.BUTTON_PAGE.route,
            modifier = modifier
        )
        {
            composable(route = MainTabs.BUTTON_PAGE.route) {
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
            composable(route = MainTabs.ANIMATION_PAGE.route) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ){
                    TestLoadingScreen()
                }
            }
            composable(route = MainTabs.COMPONENT_PAGE.route) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ){
                    Text("不知道組合三毀")
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSampleTheme {
        Greeting("Android")
    }
}