package com.example.projectworkaprilkumak.Home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.adapters.ImpactAdapter
import com.example.projectworkaprilkumak.adapters.MainCategoryAdapter
import com.example.projectworkaprilkumak.adapters.PrayerAdapter
import com.example.projectworkaprilkumak.adapters.UrgentFAdapter
import com.example.projectworkaprilkumak.databinding.FragmentHomeBinding
import com.example.projectworkaprilkumak.datas.ImpactData
import com.example.projectworkaprilkumak.datas.MainCategory
import com.example.projectworkaprilkumak.datas.Prayers
import com.example.projectworkaprilkumak.datas.UrgentFdata

class HomeFragment : Fragment() {

    private lateinit var urgents_adapter: UrgentFAdapter
    private lateinit var endings_adapter: UrgentFAdapter
    private lateinit var impactsAdapter: ImpactAdapter
    private lateinit var urgents: MutableList<UrgentFdata>
    private lateinit var impacts: MutableList<ImpactData>
    private lateinit var prayers: MutableList<Prayers>
    private lateinit var prayers_adapter: PrayerAdapter

    private lateinit var bookmarkedList: MutableList<UrgentFdata>


    private lateinit var endings: MutableList<UrgentFdata>
    private lateinit var binding: FragmentHomeBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)




        var toolbar: Toolbar = binding.toolbar3
        val activity: AppCompatActivity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)


        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)


        bookmarkedList = arrayListOf()
        val list = loadUF()

        urgents_adapter = UrgentFAdapter(list, object : UrgentFAdapter.DonateInterface{
            override fun onPress(donationData: UrgentFdata) {
                val bundle = bundleOf("donationData" to donationData)
                findNavController().navigate(R.id.donationDetailsFragment, bundle)
            }

        }, object : UrgentFAdapter.BookMarkInterface{
            override fun b_click(donationData: UrgentFdata) {
                bookmarkedList.add(donationData)
                Log.d("MyData0", "${bookmarkedList}")
            }
        })

        binding.urgentRV.adapter = urgents_adapter
        binding.urgentRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.categoryRV.adapter = MainCategoryAdapter(MainCategory.values(), object : MainCategoryAdapter.MyCategoryInterface{
            override fun onItemClick(category: MainCategory, position: Int) {
                var categoryList = mutableListOf<UrgentFdata>()
                if (category.categoryName=="All"){
                    categoryList.addAll(list)
                }
                list.forEach{
                    if (it.category==category) categoryList.add(it)
                }
                urgents_adapter = UrgentFAdapter(categoryList, object : UrgentFAdapter.DonateInterface{
                    override fun onPress(donationData: UrgentFdata) {
                        val bundle = bundleOf("donationData" to donationData)
                        findNavController().navigate(R.id.donationDetailsFragment, bundle)
                    }

                }, object : UrgentFAdapter.BookMarkInterface{
                    override fun b_click(donationData: UrgentFdata) {
                        bookmarkedList.add(donationData)
                        Log.d("MyData2", "${bookmarkedList}")
                    }
                })
            binding.urgentRV.adapter = urgents_adapter
            }
        })

        binding.categoryRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.urgentRV)


        endings = mutableListOf()
        list.forEach{
            if (it.u_dLeft<10){
                if (endings.size<5) {
                    endings.add(it)
                }
            }
        }



        endings_adapter = UrgentFAdapter(endings, object : UrgentFAdapter.DonateInterface{
            override fun onPress(donationData: UrgentFdata) {
                val bundle = bundleOf("donationData" to donationData)
                findNavController().navigate(R.id.donationDetailsFragment, bundle)
            }

        }, object : UrgentFAdapter.BookMarkInterface{
            override fun b_click(donationData: UrgentFdata) {
                bookmarkedList.add(donationData)
                Log.d("MyData3", "${bookmarkedList}")
            }
        })
        binding.endingRV.adapter = endings_adapter
        binding.endingRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        val impactList = loadImpact()
        impactsAdapter = ImpactAdapter(impactList, object : ImpactAdapter.ImpactInterface{
            override fun onPress(impactData: ImpactData) {
                val bundle = bundleOf("impact" to impactData)
                findNavController().navigate(R.id.impactFragment, bundle)
            }
        })


        binding.impactRV.adapter = impactsAdapter
        binding.impactRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        binding.seeAll.setOnClickListener { findNavController().navigate(R.id.urgentFragment) }
        binding.seeAll2.setOnClickListener { findNavController().navigate(R.id.endingFragment) }
        binding.seeAll3.setOnClickListener { findNavController().navigate(R.id.allImpactsFragment) }
        binding.seeAll4.setOnClickListener { findNavController().navigate(R.id.prayersFragment) }


        val prayersList = loadPrayers()
        prayers_adapter = PrayerAdapter(prayersList)
        binding.prayersRV.adapter = prayers_adapter
        binding.prayersRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        return binding.root

    }

private fun loadUF() : MutableList<UrgentFdata>{

        urgents = mutableListOf()

         urgents.add(UrgentFdata(1, R.drawable.orphanage_children, "Help Orphanage Children to..", 2379,4200,1280,19,MainCategory.ORPHANAGE))
        urgents.add(UrgentFdata(1, R.drawable.victim_volcano, "Help Victims of the Impact...", 2777, 6310, 938, 26, MainCategory.DISASTER))
        urgents.add(UrgentFdata(1, R.drawable.victim_flood, "Help Victims of Flash Flood...", 8775, 10540,4471, 9, MainCategory.DISASTER))
        urgents.add(UrgentFdata(1, R.drawable.sick_baby, "Help Little Baby to Do Stomach..", 2777, 6310, 5012, 12, MainCategory.SICKCHILD))
        urgents.add(UrgentFdata(1, R.drawable.cancer_child, "Help Kid to Overcome Cancer...", 3013, 4500, 2301, 2, MainCategory.SICKCHILD))
        urgents.add(UrgentFdata(1, R.drawable.victim_earthquake, "Help Victims of Earthquake", 4378, 7380, 2475, 5, MainCategory.DISASTER))
        urgents.add(UrgentFdata(1, R.drawable.new_school, "Help to Build a New School for kids", 5410, 12250, 3851, 3, MainCategory.INFRASTRUCTURE))
        urgents.add(UrgentFdata(1, R.drawable.hungry_kids, "Help Hungry Kids", 3850, 6723, 2104, 1, MainCategory.HUMANITY))
        urgents.add(UrgentFdata(1, R.drawable.poor_indian_student, "Help to Study in London", 2100, 2277, 577, 8, MainCategory.EDUCATION))
        urgents.add(UrgentFdata(1, R.drawable.africa_disabled_man, "Help this disabled man", 5721, 6740, 3333, 7, MainCategory.DISABLE))
        urgents.add(UrgentFdata(1, R.drawable.help_malnutrition, "Help Overcome Malnutrition", 4120, 8741, 7452, 6, MainCategory.MEDICAL))
        urgents.add(UrgentFdata(1, R.drawable.hungry_kids, "Help to Give Them Food", 1245, 2456, 1204, 1, MainCategory.HUMANITY))
        urgents.add(UrgentFdata(1, R.drawable.baby1, "Help to save this kid", 4987, 7856, 6201, 2, MainCategory.MEDICAL))

    return urgents
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

    private fun loadPrayers():MutableList<Prayers>{
        prayers = mutableListOf()

        prayers.add(Prayers(
            R.drawable.woman1,
            "Esther Howard",
            "Today",
            "It's so good to see people recover, they are sweet",
            "You and 48 others sent this prayer"))
        prayers.add(
            Prayers(
            R.drawable.man3,
            "Jake London",
            "Yesterday",
                "Finally Abraham can study in London. He was so poor in India, but in the future he'll become billionaire",
            "You and 34 others sent this prayer"))

        prayers.add(
            Prayers(
                R.drawable.man4,
                "Bill Fisher",
                "2 days ago",
                "Woow. I'm surprised how Elenora is recovering. She was diagnosed as a cancer",
                "You and 21 others sent this prayer"
            )
        )

        prayers.add(
            Prayers(
                R.drawable.woman2,
                "Elizabeth Cooper",
                "Today",
                "Hopefully, Liza will go to school tomorrow and study along her peers",
                "You and 14 others sent this prayer"
            )
        )
        prayers.add(
            Prayers(
                R.drawable.man2,
                "Joe Biden",
                "2 days ago",
                "Kids in Africa are having great time. I'm also playing with them",
                "You and 34 others sent this prayer"
            )
        )
        prayers.add(
            Prayers(
                R.drawable.man,
                "Robert Wilson",
                "Yesterday",
                "Victims of an Earthquake in Turkey are finally provided with shelter",
                "You and 56 others sent this prayer"
            )
        )

        return prayers
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.bookmark -> {
                Log.d("MyDataB", "${bookmarkedList}")
                val bundle = Bundle()
                bundle.putSerializable("bookmarkedList", bookmarkedList as ArrayList)
                findNavController().navigate(R.id.bookmarkFragment, bundle)
            }
            R.id.notification -> {
                Toast.makeText(requireContext(), "This is notification", Toast.LENGTH_SHORT)
            }
            R.id.search ->{
                findNavController().navigate(R.id.searchFragment)
            }
        }

        return super.onOptionsItemSelected(item)
    }


}