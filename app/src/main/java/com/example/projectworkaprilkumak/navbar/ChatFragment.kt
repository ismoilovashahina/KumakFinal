package com.example.projectworkaprilkumak.navbar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.databinding.FragmentChatBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentChatBinding.inflate(layoutInflater)




        return binding.root
    }

}