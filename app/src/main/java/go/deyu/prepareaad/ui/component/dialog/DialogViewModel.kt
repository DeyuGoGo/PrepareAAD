package go.deyu.prepareaad.ui.component.dialog

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.intersense.myneighbor.navi.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DialogViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigationManager: NavigationManager
) : ViewModel() {

}