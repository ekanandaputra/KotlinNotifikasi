package com.example.kotlinnotifikasi

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class FirebaseIdService : FirebaseInstanceIdService() {
    internal var dataStorage: DataStorage = DataStorage()

    fun onNewToken(token: String) {
        println(token)

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token)
    }

    override fun onTokenRefresh() {
        super.onTokenRefresh()
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        println(refreshedToken)
        Log.d("FirebaseIDService", "Refreshed token: " + refreshedToken!!)


        dataStorage.saveFcmToken(applicationContext, refreshedToken)


        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken)
    }


    private fun sendRegistrationToServer(refreshedToken: String?) {
        Log.v("Sending to server!!", "Sending==" + refreshedToken!!)
    }
}