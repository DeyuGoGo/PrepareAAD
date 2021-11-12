package go.deyu.prepareaad.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.intersense.myneighbor.navi.NavigationCommand
import com.intersense.myneighbor.navi.NavigationDirections
import com.intersense.myneighbor.navi.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import go.deyu.prepareaad.MainActivity
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigationManager: NavigationManager
) : ViewModel() {

    fun navigate(command: NavigationCommand) {
        when(command){
            NavigationDirections.buttons_root ,   NavigationDirections.animation_root , NavigationDirections.component_root ->{
                changeTabByCommand(command)
            }
        }
        navigationManager.navigate(command)

    }

    private fun changeTabByCommand(command: NavigationCommand) {
        for (tab in MainActivity.MainTabs.values()){
            if(tab.route == command){
                selectedTab.value = tab
            }
        }

    }

    val selectedTab = MutableLiveData(MainActivity.MainTabs.BUTTON_PAGE)


}
