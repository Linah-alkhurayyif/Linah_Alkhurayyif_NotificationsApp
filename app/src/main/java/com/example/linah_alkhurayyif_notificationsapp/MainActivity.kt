package com.example.linah_alkhurayyif_notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // declaring variables
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val notificationId = 1234
    private val channelId = "notificationsApp.notifications"
    private val description = "Notification App Example"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting the notification manager
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager


        notification_btn.setOnClickListener{


            val intent = Intent(this, Notification::class.java)


            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)

                //building the notification
                builder = Notification.Builder(this, channelId)
                    .setSmallIcon(R.drawable.notifications)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.notifications))
                    .setContentIntent(pendingIntent)
                    .setContentTitle("My Notification")
                    .setContentText(notification_et.text!!)
            } else {


                builder = Notification.Builder(this)
                    .setSmallIcon(R.drawable.notifications)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.notifications))
                    .setContentIntent(pendingIntent)
                    .setContentTitle("My Notification")
                    .setContentText(notification_et.text!!)
            }

            notificationManager.notify(notificationId, builder.build())



        }

    }}