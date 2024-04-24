package com.example.projectworkaprilkumak.donation_details

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.databinding.FragmentDonateBinding
import com.example.projectworkaprilkumak.databinding.FragmentEnterPinBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class EnterPinFragment : Fragment() , View.OnClickListener{
    private lateinit var binding: FragmentEnterPinBinding
    private var listPin = mutableListOf<String>()
    private var oldListPin = mutableListOf<String>()
    private lateinit var getPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEnterPinBinding.inflate(inflater, container, false)
        var toolbar: Toolbar = binding.toolbar
        val activity: AppCompatActivity = getActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)

        toolbar.setNavigationOnClickListener { findNavController().navigate(R.id.donateFragment) }


        val type = object : TypeToken<List<String>>() {}.type
        val gson = Gson()
        getPreferences = this.requireActivity().getSharedPreferences("pin_code", AppCompatActivity.MODE_PRIVATE)
        val str = getPreferences.getString("Users", "")
        if (str == "") {
        } else {
            oldListPin = gson.fromJson(str, type)
        }

        loadNumbers()

        binding.continueBtn.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.custom_dialog_window_making_donation)
            val next = dialog.findViewById(R.id.continue_btn) as Button
            next.setOnClickListener { findNavController().navigate(R.id.homeFragment)
                dialog.dismiss()}
            dialog.show()
        }





        return binding.root
    }


    private fun loadNumbers() {
        binding.one.setOnClickListener(this)
        binding.two.setOnClickListener(this)
        binding.three.setOnClickListener(this)
        binding.four.setOnClickListener(this)
        binding.five.setOnClickListener(this)
        binding.six.setOnClickListener(this)
        binding.seven.setOnClickListener(this)
        binding.eight.setOnClickListener(this)
        binding.nine.setOnClickListener(this)
        binding.zero.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val animationOut = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_shake)
        if (listPin.size < 4) {
            listPin.add(p0!!.tag.toString())
            when (listPin.size) {
                1 -> binding.pin1.setBackgroundResource(R.drawable.checkbox_oval_checked)
                2 -> binding.pin2.setBackgroundResource(R.drawable.checkbox_oval_checked)
                3 -> binding.pin3.setBackgroundResource(R.drawable.checkbox_oval_checked)
                4 -> binding.pin4.setBackgroundResource(R.drawable.checkbox_oval_checked)
            }
        }
        if (listPin.size == 4) {
            if (oldListPin == listPin) {
               binding.continueBtn.visibility=View.VISIBLE
            } else {
                binding.listPin.startAnimation(animationOut)
                Toast.makeText(
                    requireContext(),
                    "Wrong pin-code",
                    Toast.LENGTH_SHORT
                ).show()
                listPin.clear()
                binding.pin1.setBackgroundResource(R.drawable.checkbox_oval)
                binding.pin2.setBackgroundResource(R.drawable.checkbox_oval)
                binding.pin3.setBackgroundResource(R.drawable.checkbox_oval)
                binding.pin4.setBackgroundResource(R.drawable.checkbox_oval)
            }
        }
    }


}