package com.example.projectworkaprilkumak.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.adapters.ImpactAdapter
import com.example.projectworkaprilkumak.databinding.FragmentHomeBinding
import com.example.projectworkaprilkumak.databinding.FragmentImpactBinding
import com.example.projectworkaprilkumak.datas.ImpactData


class ImpactFragment : Fragment() {

    private lateinit var binding: FragmentImpactBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImpactBinding.inflate(inflater, container, false)
        var impactCard = arguments?.getSerializable("impact") as ImpactData

        binding.impactI.setImageResource(impactCard.impactI)
        binding.impactT.text = impactCard.impactT

        binding.back.setOnClickListener { findNavController().navigate(R.id.homeFragment) }




        return binding.root
    }




}