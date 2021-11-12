package go.deyu.prepareaad.ui.component.location

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun ShowLocationScreen(viewModel: ShowLocationViewModel) {
    val myLocation by viewModel.myLocation.observeAsState(null)
    val doNotShowRationale = rememberSaveable { mutableStateOf(false) }
    val locationPermissionState =
        rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    PermissionRequired(
        permissionState = locationPermissionState,
        permissionNotGrantedContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (doNotShowRationale.value) {
                    Text("無法定位，無法驗證該地區。")
                } else {
                    Text("區域驗證需要定位權限，請把定位權限打開。")
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Button(

                            onClick = { locationPermissionState.launchPermissionRequest() }
                        ) { Text(text = "好") }
                        Spacer(Modifier.width(8.dp))
                        Button(onClick = { doNotShowRationale.value = true }) {
                            Text(text = "取消")
                        }
                    }
                }
            }
        },
        permissionNotAvailableContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "請允許定位權限，以幫您的位置做定位。",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = viewModel::openSetting,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("開啟設定")
                }
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = { viewModel.getLastLocation() }) {
                Text("LastLocation")
            }
            Button(onClick = { viewModel.startLocationFlow()}) {
                Text("startFlow")
            }
            myLocation?.run {
                Text(text = "我最後的位置在 ${myLocation}")
            }
        }
    }
}