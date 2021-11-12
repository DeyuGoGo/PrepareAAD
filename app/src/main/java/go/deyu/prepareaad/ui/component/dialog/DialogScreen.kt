package go.deyu.prepareaad.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TestDialogScreen() {
//    控制Dialog開關用的
    var openDialog by remember { mutableStateOf(false) }
// 你的資料集合，這邊方便用String 替代。
    val list by remember { mutableStateOf(listOf("一號", "二號", "三號")) }
    // 目前選擇的Item 這邊初始指向最後一筆資料。
    var selectedItem by remember { mutableStateOf(list.last()) }


//    隨便弄個佈局 一個按鈕打開Dialog，然後一個顯示現在選擇的字串。
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { openDialog = true }) {
            Text(text = "打開選擇 Dialog")
        }
        Text(text = "目前選擇的是$selectedItem")
    }

//    當Boolean為True時顯示
    if (openDialog) {
        ChoiceDialog(
            items = list,
            selected = selectedItem,
            //            Item被按下去的時候
            onItemClick = { item ->
                selectedItem = item
                openDialog = false

            },
            onDismiss = { openDialog = false },
//            你要顯示的Item畫面
            contextView = { StringItemView(it) }
        )
    }

}

@Composable
fun <T> ChoiceDialog(
    items: List<T>,
    selected: T?,
    onItemClick: (item: T) -> Unit,
    contextView: @Composable (T) -> Unit,
    onDismiss: () -> Unit
) {
//    這邊T只是方便你
//    主要就是用Dialog元件做顯示，然後item click的時候把事件傳導出去。
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        content = {
            LazyColumn(
                Modifier
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colors.background)
                    .padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(items) { item ->
                    Button(
                        onClick = { onItemClick(item) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = if (selected == item) Color.Green else Color.Transparent)
                    ) {
                        contextView(item)
                    }
                }
            }
        }
    )
}

@Composable
fun StringItemView(item: String) {
    Text(text = "item $item")
}