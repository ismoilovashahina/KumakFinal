package com.example.projectworkaprilkumak.actionbar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.adapters.MainCategoryAdapter
import com.example.projectworkaprilkumak.adapters.UrgentFAdapter
import com.example.projectworkaprilkumak.databinding.FragmentSearchBinding
import com.example.projectworkaprilkumak.datas.MainCategory
import com.example.projectworkaprilkumak.datas.UrgentFdata

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchCards: MutableList<UrgentFdata>
    private lateinit var searchCardsAdapter: UrgentFAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        var toolbar: Toolbar = binding.toolbar
        val activity: AppCompatActivity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)

        toolbar.setNavigationOnClickListener { findNavController().navigate(R.id.homeFragment) }

        val list = loadSF()

        binding.search.addTextChangedListener {
            val filter = mutableListOf<UrgentFdata>()
            if (it != null) {
                for (c in list) {
                    if (c.u_t.lowercase().contains(it.toString().lowercase())) {
                        filter.add(c)
                        binding.resultNumber.text = filter.size.toString()
                    }
                }
                searchCardsAdapter = UrgentFAdapter(filter, object : UrgentFAdapter.DonateInterface{
                    override fun onPress(donationData: UrgentFdata) {
                        val bundle = bundleOf("donationData" to donationData)
                        findNavController().navigate(R.id.donationDetailsFragment, bundle)
                    }

                }, object : UrgentFAdapter.BookMarkInterface{
                    override fun b_click(donationData: UrgentFdata) {
                        val bundle = bundleOf("bookmark" to donationData)
                        findNavController().navigate(R.id.bookmarkFragment, bundle)
                    }

                })
                binding.searchRV.adapter = searchCardsAdapter
            }
        }

        searchCardsAdapter = UrgentFAdapter(list, object : UrgentFAdapter.DonateInterface{
            override fun onPress(donationData: UrgentFdata) {
                val bundle = bundleOf("donationData" to donationData)
                findNavController().navigate(R.id.donationDetailsFragment, bundle)
            }

        }, object : UrgentFAdapter.BookMarkInterface{
            override fun b_click(donationData: UrgentFdata) {
                val bundle = bundleOf("bookmark" to donationData)
                findNavController().navigate(R.id.bookmarkFragment, bundle)
            }

        })
        binding.searchRV.adapter = searchCardsAdapter
        binding.searchRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.categoryRV.adapter = MainCategoryAdapter(MainCategory.values(), object : MainCategoryAdapter.MyCategoryInterface{
            override fun onItemClick(category: MainCategory, position: Int) {
                var categoryList = mutableListOf<UrgentFdata>()
                if (category.categoryName=="All"){
                    categoryList.addAll(list)
                }
                list.forEach{
                    if (it.category==category) categoryList.add(it)
                }
                searchCardsAdapter = UrgentFAdapter(categoryList, object : UrgentFAdapter.DonateInterface{
                    override fun onPress(donationData: UrgentFdata) {
                        val bundle = bundleOf("donationData" to donationData)
                        findNavController().navigate(R.id.donationDetailsFragment, bundle)
                    }

                }, object : UrgentFAdapter.BookMarkInterface{
                    override fun b_click(donationData: UrgentFdata) {
                        val bundle = bundleOf("bookmark" to donationData)
                        findNavController().navigate(R.id.bookmarkFragment, bundle)
                    }

                })
                binding.searchRV.adapter = searchCardsAdapter
            }
        })

        binding.categoryRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.searchRV)




        return binding.root
    }

    private fun loadSF() : MutableList<UrgentFdata>{
        searchCards = mutableListOf()


       for (i in 1..2){
           searchCards.add(
               UrgentFdata(2, R.drawable.orphanage_children, "Help Orphanage Children to..", 2379,4200,1280,19,
                   MainCategory.HUMANITY)
           )
           searchCards.add(UrgentFdata(2, R.drawable.victim_volcano, "Help Victims of the Impact...", 2777, 6310, 938, 26, MainCategory.DISASTER))
           searchCards.add(UrgentFdata(2, R.drawable.victim_flood, "Help Victims of Flash Flood...", 8775, 10540,4471, 9, MainCategory.DISASTER))
           searchCards.add(UrgentFdata(2, R.drawable.sick_baby, "Help Little Baby to Do Stomach..", 2777, 6310, 5012, 12, MainCategory.SICKCHILD))
           searchCards.add(UrgentFdata(2, R.drawable.cancer_child, "Help Kid to Overcome Cancer...", 3013, 4500, 2301, 2, MainCategory.SICKCHILD))
           searchCards.add(UrgentFdata(2, R.drawable.victim_earthquake, "Help Victims of Earthquake", 4378, 7380, 2475, 5, MainCategory.DISASTER))
           searchCards.add(UrgentFdata(2, R.drawable.new_school, "Help to Build a New School for kids", 5410, 12250, 3851, 3, MainCategory.INFRASTRUCTURE))
           searchCards.add(UrgentFdata(2, R.drawable.hungry_kids, "Help Hungry Kids", 3850, 6723, 2104, 1, MainCategory.HUMANITY))
           searchCards.add(UrgentFdata(2, R.drawable.hungry_kids, "Help Hungry Kids", 3850, 6723, 2104, 1, MainCategory.HUMANITY))
           searchCards.add(UrgentFdata(2, R.drawable.poor_indian_student, "Help to Study in London", 2100, 2277, 577, 8, MainCategory.EDUCATION))
           searchCards.add(UrgentFdata(2, R.drawable.africa_disabled_man, "Help this disabled man", 5721, 6740, 3333, 7, MainCategory.DISABLE))
           searchCards.add(UrgentFdata(2, R.drawable.help_malnutrition, "Help Overcome Malnutrition", 4120, 8741, 7452, 6, MainCategory.MEDICAL))
           searchCards.add(UrgentFdata(2, R.drawable.hungry_kids, "Help to Give Them Food", 1245, 2456, 1204, 1, MainCategory.HUMANITY))
           searchCards.add(UrgentFdata(2, R.drawable.baby1, "Help to save this kid", 4987, 7856, 6201, 2, MainCategory.MEDICAL))

       }


        return searchCards
    }


}