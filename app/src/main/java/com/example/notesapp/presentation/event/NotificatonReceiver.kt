package com.example.notesapp.presentation.event

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.notesapp.R

const val NOTIFICATION_ID = 1
const val chanelId = "channel1"
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"

class NotificationReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        val notification= NotificationCompat.Builder(context, chanelId)
            .setSmallIcon(R.drawable.ic_notes_2)
            .setContentTitle(intent.getStringExtra(titleExtra))
            .setContentText(intent.getStringExtra(messageExtra))
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }

}