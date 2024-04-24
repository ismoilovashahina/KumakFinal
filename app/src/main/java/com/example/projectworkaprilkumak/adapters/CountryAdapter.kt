package com.example.projectworkaprilkumak.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.databinding.FragmentSelectCountryBinding
import com.example.projectworkaprilkumak.databinding.ItemCountryBinding
import com.example.projectworkaprilkumak.datas.Country

class CountryAdapter(context: Context, var countries: MutableList<Country>,var onClick: ClickMe):
    ArrayAdapter<Country>(context, R.layout.item_country, countries) {

    override fun getCount(): Int {
        return countries.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var binding:ItemCountryBinding
        if(convertView == null){
            binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else{
            binding = ItemCountryBinding.bind(convertView)
        }
        val country = countries[position]
        binding.flagImg.setImageResource(country.flag)
        binding.countryName.text = country.name
        binding.countryShortName.text = country.shortName
        var state = true
        binding.countryCard.setOnClickListener{
            if(state){
                binding.ch1.setBackgroundResource(R.drawable.checkbox_oval_checked)
                state=false
            }
            else{
                binding.ch1.setBackgroundResource(R.drawable.checkbox_oval)
                state=true
            }
            onClick.onClick(country)
        }




        return binding.root
    }

    interface ClickMe{
        fun onClick(country: Country)
    }
}