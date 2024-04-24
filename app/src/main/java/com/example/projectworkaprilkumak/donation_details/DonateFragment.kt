package com.example.projectworkaprilkumak.donation_details

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
import com.example.projectworkaprilkumak.adapters.DonateAdapter
import com.example.projectworkaprilkumak.adapters.MainCategoryAdapter
import com.example.projectworkaprilkumak.adapters.UrgentFAdapter
import com.example.projectworkaprilkumak.databinding.FragmentDonateBinding
import com.example.projectworkaprilkumak.datas.DonateData
import com.example.projectworkaprilkumak.datas.MainCategory
import com.example.projectworkaprilkumak.datas.UrgentFdata

class DonateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDonateBinding.inflate(inflater,container,false)

        var toolbar: Toolbar = binding.toolbar
        val activity: AppCompatActivity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)

        toolbar.setNavigationOnClickListener { findNavController().navigate(R.id.donationDetailsFragment) }

        binding.amountRV.layoutManager = GridLayoutManager(context, 2)

        var amounts = mutableListOf<DonateData>()
        amounts.add(DonateData("$5"))
        amounts.add(DonateData("$10"))
        amounts.add(DonateData("$25"))
        amounts.add(DonateData("$50"))
        amounts.add(DonateData("$100"))
        amounts.add(DonateData("$200"))

        binding.amountRV.adapter = DonateAdapter(amounts, object : DonateAdapter.MyAmountInterface{
            override fun onClick(amount: DonateData, position: Int) {
                binding.amountText.text=amount.amount
                binding.continueBtn.visibility = View.VISIBLE
            }

        })

        binding.continueBtn.setOnClickListener { findNavController().navigate(R.id.enterPinFragment) }





        return binding.root
    }

}