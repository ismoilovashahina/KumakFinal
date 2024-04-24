package com.example.projectworkaprilkumak.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.databinding.ItemNotificationBinding
import com.example.projectworkaprilkumak.datas.NotificationData

class NotificationAdapter(context: Context, var notifications: MutableList<NotificationData>) :
    ArrayAdapter<NotificationData>(context, R.layout.item_notification, notifications) {

    override fun getCount(): Int {
        return notifications.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding:ItemNotificationBinding
        if (convertView == null){
            binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else{
            binding = ItemNotificationBinding.bind(convertView)
        }
        val notification = notifications[position]
        binding.notificationTtitle.text = notification.notificationTitle
        binding.notificationDescription.text = notification.notificationDescription

        return binding.root
    }
}