package go.deyu.prepareaad.recevier

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.RemoteInput
import timber.log.Timber

class MyReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_TEST = "action_test_receiver"
        const val ACTION_REMOTE_NOTIFICATION = "action_remote_notification"
        const val KEY_TEXT_REPLY = "key_text_reply"

    }

    override fun onReceive(context: Context, intent: Intent) {
        when(val action = intent.action){
            ACTION_TEST -> {
                Timber.d("MyReceiver receive $action")
            }
            ACTION_REMOTE_NOTIFICATION ->{
                Timber.d("MyReceiver receive $action  with String : ${getMessageText(intent)}")
            }
        }
    }

    private fun getMessageText(intent: Intent): CharSequence? {
        return RemoteInput.getResultsFromIntent(intent)?.getCharSequence(KEY_TEXT_REPLY)
    }

}