package go.deyu.prepareaad

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Trace
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.intersense.myneighbor.navi.NavigationCommand
import com.intersense.myneighbor.navi.NavigationDirections
import com.intersense.myneighbor.navi.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import go.deyu.prepareaad.ui.main.MainScreen
import go.deyu.prepareaad.ui.main.MainViewModel
import go.deyu.prepareaad.ui.theme.ComposeSampleTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationManager: NavigationManager

    val viewModel: MainViewModel by viewModels()

    enum class MainTabs(
        val title: String,
        val icon: ImageVector,
        val route: NavigationCommand
    ) {
        BUTTON_PAGE("按鈕", icon = Icons.Default.SmartButton, NavigationDirections.buttons_root),
        ANIMATION_PAGE("動畫", Icons.Default.Animation, NavigationDirections.animation_root),
        COMPONENT_PAGE("元件", Icons.Default.ViewCompact, NavigationDirections.component_root)
    }


    @ExperimentalPermissionsApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Trace.beginSection("DeyuTrace")
        setContent {
            ComposeSampleTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(navController = navController, viewModel = viewModel)
                }
                navigationManager.commands.collectAsState().value.also { command ->
                    when (command) {
                        NavigationDirections.none -> {

                        }
                        NavigationDirections.back -> {
                            navController.popBackStack()
                        }
                        NavigationDirections.open_location_permission -> {
                            openAppLocationPermission()
                        }
                        else -> {
                            if (command.destination.isNotEmpty()) navController.navigate(command.destination) {
                                this.launchSingleTop = true
                            }
                        }
                    }
                }
            }
        }
        Trace.endSection()
    }

    private fun openAppLocationPermission() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts(
            "package",
            BuildConfig.APPLICATION_ID,
            null
        )
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        navigationManager.navigate(NavigationDirections.none)
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