package com.example.projectworkaprilkumak.actionbar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.adapters.NotificationAdapter
import com.example.projectworkaprilkumak.databinding.FragmentNotificationBinding
import com.example.projectworkaprilkumak.datas.NotificationData


class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private var notificationsList = mutableListOf<NotificationData>()
    private lateinit var notificationAdapter: NotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)

        var toolbar: Toolbar = binding.toolbar
        val activity: AppCompatActivity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)



        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }




        return binding.root
    }

}

