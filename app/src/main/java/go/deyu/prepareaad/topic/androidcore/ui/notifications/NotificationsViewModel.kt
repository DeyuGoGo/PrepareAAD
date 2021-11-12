package go.deyu.prepareaad.topic.androidcore.ui.notifications

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import go.deyu.prepareaad.databinding.FragmentNotificationsBinding
import go.deyu.prepareaad.notification.NotificationUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(private val notificationUtil: NotificationUtil) :
    ViewModel() {


    fun notifyNotification(type: NotificationUtil.NotificationType = NotificationUtil.NotificationType.BASIC) {
        when(type){
            is NotificationUtil.NotificationType.PROGRESS -> {
                notificationProgress()
            }
            else -> notificationUtil.showNotificationSample(type = type)
        }
    }

    private fun notificationProgress(){
        flow{
           for( i in 0..100){
               delay(500)
               emit(i)
           }
        }.onEach {
            notificationUtil.showNotificationSample(type = NotificationUtil.NotificationType.PROGRESS(progress = it))
        }.launchIn(viewModelScope)
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text


}