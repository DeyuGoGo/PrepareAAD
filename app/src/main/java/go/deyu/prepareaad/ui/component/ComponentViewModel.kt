package go.deyu.prepareaad.ui.component

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.intersense.myneighbor.navi.NavigationCommand
import com.intersense.myneighbor.navi.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComponentViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigationManager: NavigationManager,
) : ViewModel() {

    fun navigate(command: NavigationCommand) {
        navigationManager.navigate(command)
    }

}