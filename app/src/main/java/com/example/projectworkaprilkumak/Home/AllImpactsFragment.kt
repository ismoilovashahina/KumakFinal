package com.example.projectworkaprilkumak.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.adapters.ImpactAdapter
import com.example.projectworkaprilkumak.databinding.FragmentAllImpactsBinding
import com.example.projectworkaprilkumak.datas.ImpactData


class AllImpactsFragment : Fragment() {
private lateinit var binding:FragmentAllImpactsBinding
    private lateinit var impacts: MutableList<ImpactData>
    private lateinit var impactsAdapter: ImpactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllImpactsBinding.inflate(inflater, container, false)


        var toolbar: Toolbar = binding.toolbar
        val activity: AppCompatActivity = getActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)

        toolbar.setNavigationOnClickListener { findNavController().navigate(R.id.homeFragment) }


        val impactList = loadImpact()
        impactsAdapter = ImpactAdapter(impactList, object : ImpactAdapter.ImpactInterface{
            override fun onPress(impactData: ImpactData) {
                val bundle = bundleOf("impact" to impactData)
                findNavController().navigate(R.id.impactFragment, bundle)
            }


        })
        binding.impactRV.adapter = impactsAdapter
        binding.impactRV.layoutManager = GridLayoutManager(context, 2)





        return binding.root
    }

    private fun loadImpact() : MutableList<ImpactData>{
        impacts = mutableListOf()

        impacts.add(ImpactData(R.drawable.siamese_twins, "Sarah's surgery was successful"))
        impacts.add(ImpactData(R.drawable.help_malnutrition, "Help Improve Nutrition for Africe"))
        impacts.add(ImpactData(R.drawable.victim_flood, "Help Victims of Flash Flood"))
        impacts.add(ImpactData(R.drawable.hungry_kids, "Help Improve Nutrition for India"))
        impacts.add(ImpactData(R.drawable.new_school, "Help to Build a New School for kids"))

        return impacts
    }

}