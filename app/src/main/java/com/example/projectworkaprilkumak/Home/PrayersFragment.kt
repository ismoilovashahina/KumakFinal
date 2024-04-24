package com.example.projectworkaprilkumak.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.adapters.PrayerAdapter
import com.example.projectworkaprilkumak.databinding.FragmentPrayersBinding
import com.example.projectworkaprilkumak.datas.Prayers

class PrayersFragment : Fragment() {

    private lateinit var binding: FragmentPrayersBinding
    private lateinit var prayers: MutableList<Prayers>
    private lateinit var prayers_adapter: PrayerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrayersBinding.inflate(inflater, container, false)

        requireActivity()

        // buyogamas
        //navbar package ni ichidagilarga
        var toolbar: Toolbar = binding.toolbar
        val activity: AppCompatActivity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)

        toolbar.setNavigationOnClickListener { findNavController().navigate(R.id.homeFragment) }


        val prayersList = loadPrayers()
        prayers_adapter = PrayerAdapter(prayersList)

        binding.prayersRecyclerView.setHasFixedSize(true)
        binding.prayersRecyclerView.adapter = prayers_adapter
        binding.prayersRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }


    private fun loadPrayers():MutableList<Prayers>{
        prayers = mutableListOf()

        prayers.add(
            Prayers(
            R.drawable.woman1,
            "Esther Howard",
            "Today",
            "It's so good to see people recover, they are sweet",
            "You and 48 others sent this prayer")
        )
        prayers.add(
            Prayers(
                R.drawable.man3,
                "Jake London",
                "Yesterday",
                "Finally Abraham can study in London. He was so poor in India, but in the future he'll become billionaire",
                "You and 34 others sent this prayer")
        )

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


}