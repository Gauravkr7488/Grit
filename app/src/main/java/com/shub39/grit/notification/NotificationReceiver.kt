package com.shub39.grit.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.shub39.grit.database.habit.Habit
import java.time.LocalDateTime

class NotificationReceiver: BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("NotificationReceiver", "Received")
        val scheduler = NotificationAlarmScheduler(context)
        if (intent != null) {
            val habitId = intent.getStringExtra("1") ?: return
            val habitDescription = intent.getStringExtra("2") ?: return
            habitNotification(context, Habit(habitId, habitDescription, LocalDateTime.now()))
            scheduler.schedule(Habit(habitId, habitDescription, LocalDateTime.now()))
        }
    }

}