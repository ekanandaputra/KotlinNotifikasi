package com.example.kotlinnotifikasi

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.sql.Types.NULL

class FirebaseMessageService : FirebaseMessagingService() {
    private val TAG = "FirebaseMessageService"


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        if(remoteMessage!!.data != null){
            sendNotification(remoteMessage)
        }

    }

    private fun sendNotification(remoteMessage: RemoteMessage?) {

        val title = remoteMessage!!.data["title"]
        val content = remoteMessage!!.data["content"]

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId: String = getString(R.string.app_name);

        @RequiresApi(Build.VERSION_CODES.O)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channelId, "Notifikasi", NotificationManager.IMPORTANCE_MAX)
            notificationChannel.description = "Kotlin Notif"
            notificationChannel.enableLights(true)
            notificationChannel.vibrationPattern = longArrayOf(0,1000,500,500)
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)

        notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.notification_icon_background)
            .setContentTitle(title)
            .setContentText(content)
        notificationManager.notify(1, notificationBuilder.build())
    }
}