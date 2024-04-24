package com.example.projectworkaprilkumak.onboarding.accaunt_setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.adapters.CountryAdapter
import com.example.projectworkaprilkumak.database.MyBase
import com.example.projectworkaprilkumak.databinding.FragmentSelectCountryBinding
import com.example.projectworkaprilkumak.datas.Country
import com.example.projectworkaprilkumak.datas.MyCountry


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2" 

class SelectCountryFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var coutry:Country? = null
    private var listC = mutableListOf<Country>()
    private lateinit var adapter: CountryAdapter
    lateinit var myBase: MyBase
    lateinit var list:ArrayList<MyCountry>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myBase = MyBase(requireContext())
        list = ArrayList()
        createCountry()
        val binding = FragmentSelectCountryBinding.inflate(inflater, container, false)

        var toolbar: Toolbar = binding.toolbar
        val activity : AppCompatActivity = getActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)

        toolbar.setNavigationOnClickListener { findNavController().navigate(R.id.signUpFragment )}


        adapter = CountryAdapter(requireContext(), listC,object :CountryAdapter.ClickMe{
            override fun onClick(country: Country) {
                coutry = Country(country.name,country.flag,country.shortName)
            }
        })
        binding.countryList.adapter = adapter



        binding.continueBtn.setOnClickListener {
            if (coutry!!.name.isNotEmpty()){
                myBase.addCountry(MyCountry(coutry!!.name,coutry!!.flag,coutry!!.shortName))
                findNavController().navigate(R.id.fillProfile)
            }
           // findNavController().navigate(R.id.fillProfile)
        }

        binding.search.addTextChangedListener {
            val filter = mutableListOf<Country>()
            if (it != null) {
                for (c in listC) {
                    if (c.name.lowercase().contains(it.toString().lowercase())) {
                        filter.add(c)
                    }
                }
                adapter = CountryAdapter(requireContext(), filter,object :CountryAdapter.ClickMe{
                    override fun onClick(country: Country) {
                        // nothing here
                        // yozish shart emas
                    }
                })
                binding.countryList.adapter = adapter
            }
        }

        return binding.root
    }

    private fun createCountry() {

        for(i in 0..20){
            listC.add(Country("Afghanistan", R.drawable.flag_afghan, "AF"))
            listC.add(Country("India", R.drawable.flag_india, "In"))
            listC.add(Country("Malaysia", R.drawable.flag_malaysia, "Ml"))
            listC.add(Country("USA", R.drawable.flag_usa, "US"))
        }

    }
}