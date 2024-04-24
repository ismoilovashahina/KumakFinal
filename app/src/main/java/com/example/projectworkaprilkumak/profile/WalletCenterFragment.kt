package com.example.projectworkaprilkumak.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.databinding.FragmentWalletCenterBinding


class WalletCenterFragment : Fragment() {

    private lateinit var binding: FragmentWalletCenterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWalletCenterBinding.inflate(inflater, container, false)

        return binding.root
    }

}