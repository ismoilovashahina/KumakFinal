package com.example.projectworkaprilkumak.donation_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.databinding.FragmentDonationDetailsBinding
import com.example.projectworkaprilkumak.datas.UrgentFdata



class DonationDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDonationDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonationDetailsBinding.inflate(inflater, container, false)
        var donationCard = arguments?.getSerializable("donationData") as UrgentFdata

        binding.DDImage.setImageResource(donationCard.u_i)
        binding.urgentTitle.text = donationCard.u_t
        binding.urgentRaisedFund.text = donationCard.u_raised.toString()
        binding.urgentToRaise.text = donationCard.u_raised.toString()
        binding.urgentDonatorQuantity.text = donationCard.u_don.toString()
        binding.UrgentLeftDays.text = donationCard.u_dLeft.toString()
        binding.categoryText.text = donationCard.category.categoryName
        binding.urgentDonatorQuantity2.text = donationCard.u_don.toString()


        binding.back.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        binding.donateBtn.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        return binding.root
    }

}