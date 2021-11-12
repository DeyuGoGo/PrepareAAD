package go.deyu.prepareaad.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.intersense.myneighbor.navi.NavigationDirections

@Composable
fun ComponentRootScreen(viewModel: ComponentViewModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TextButton(onClick = { viewModel.navigate(NavigationDirections.component_location) }) {
            Text("Location")
        }
        TextButton(onClick = { viewModel.navigate(NavigationDirections.component_dialog) }) {
            Text("Dialog")
        }
    }
}