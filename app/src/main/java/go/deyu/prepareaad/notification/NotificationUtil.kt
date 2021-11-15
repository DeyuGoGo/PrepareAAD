package go.deyu.prepareaad.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import go.deyu.prepareaad.MainActivity
import go.deyu.prepareaad.R
import go.deyu.prepareaad.recevier.MyReceiver
import go.deyu.prepareaad.recevier.MyReceiver.Companion.ACTION_TEST

class NotificationUtil(val context: Context) {

    private val CHANNEL_ID = "頻道ID"
    private val CHANNEL_NAME = "頻道名稱"
    private val NOTIFCONNECT_ID = 0x87
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val basicNotificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Title")
        .setContentText("content")

    init {
        createChannel()
    }

    fun showNotificationSample(type: NotificationType) {
        when (type) {
            NotificationType.BASIC -> notificationManager.notify(
                NOTIFCONNECT_ID,
                basicNotification()
            )
            NotificationType.BUTTON -> notificationManager.notify(
                NOTIFCONNECT_ID,
                notificationWithButton()
            )
            NotificationType.REPLY -> notificationManager.notify(
                NOTIFCONNECT_ID,
                replyNotification()
            )
            is NotificationType.PROGRESS -> {
                notificationManager.notify(NOTIFCONNECT_ID, progressNotification(type.progress))
            }
            is NotificationType.MESSAGE -> notificationManager.notify(
                NOTIFCONNECT_ID,
                meesageNotification()
            )
        }

    }

    private fun replyNotification(): Notification {
//        輸入字串
        val replyLabel: String = "回覆"
        val remoteInput: RemoteInput = RemoteInput.Builder(MyReceiver.KEY_TEXT_REPLY).run {
            setLabel(replyLabel)
            build()
        }
        val snoozeIntent = Intent(context, MyReceiver::class.java).apply {
            action = MyReceiver.ACTION_REMOTE_NOTIFICATION
        }
        val replyPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(
                context,
                1,
                snoozeIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        val action: NotificationCompat.Action =
            NotificationCompat.Action.Builder(
                R.drawable.ic_launcher_foreground,
                "SDFAF", replyPendingIntent
            )
                .addRemoteInput(remoteInput)
                .build()

        return basicNotificationBuilder
            .addAction(action)
            .build()

    }

    private fun basicNotification(): Notification {
        return basicNotificationBuilder
            .setColorized(true)
            .setStyle(androidx.media.app.NotificationCompat.DecoratedMediaCustomViewStyle())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }

    private fun progressNotification(progress: Int): Notification {
        return basicNotificationBuilder.apply {
            setProgress(100, progress, false)
            setPriority(NotificationCompat.PRIORITY_LOW)
        }.build()
    }

    private fun meesageNotification(): Notification {
        return basicNotificationBuilder
            .setStyle(
                NotificationCompat.MessagingStyle("Me")
                    .setConversationTitle("Team lunch")
                    .addMessage(
                        "Hi",
                        System.currentTimeMillis() - 1000L,
                        ""
                    ) // Pass in null for user.
                    .addMessage("What's up?", System.currentTimeMillis() - 1000L, "Coworker")
                    .addMessage("Not much", System.currentTimeMillis() - 1000L, "")
                    .addMessage("How about lunch?", System.currentTimeMillis() - 1000L, "Coworker")
            )
            .build()
    }


    private fun notificationWithButton(): Notification {
        val snoozeIntent = Intent(context, MyReceiver::class.java).apply {
            action = ACTION_TEST
        }
        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val snoozePendingIntent: PendingIntent =
            PendingIntent.getBroadcast(context, 0, snoozeIntent, 0)
        return basicNotificationBuilder
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)// 按通知的Intent
            .addAction(
                R.drawable.ic_launcher_foreground, "按我啊",
                snoozePendingIntent // 按鈕的Intent
            ).build()
    }


    //     Android 8.0以上必須先建立通知Channel 才能使用通知。
//    Channel
    private fun createChannel() {
        val notificationManager = context.getSystemService(
            NotificationManager::class.java
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "通知頻道說明"

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    sealed class NotificationType {
        object BASIC : NotificationType()
        object BUTTON : NotificationType()
        object REPLY : NotificationType()
        class PROGRESS(val progress: Int) : NotificationType()
        object MESSAGE : NotificationType()
    }

}