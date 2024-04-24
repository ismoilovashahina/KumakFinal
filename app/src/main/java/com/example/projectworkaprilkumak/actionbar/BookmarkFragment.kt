package com.example.projectworkaprilkumak.actionbar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.adapters.BookmarkAdapter
import com.example.projectworkaprilkumak.adapters.MainCategoryAdapter
import com.example.projectworkaprilkumak.adapters.UrgentFAdapter
import com.example.projectworkaprilkumak.databinding.FragmentBookmarkBinding
import com.example.projectworkaprilkumak.datas.MainCategory
import com.example.projectworkaprilkumak.datas.UrgentFdata
import com.example.projectworkaprilkumak.sharedPreferences.MySharedPreferences
//        val file = MySharedPreferences.getInstance(this.requireActivity())
//        bookmarkList = file.getPost()
//        bookmarkList.add(bookmarkF)
//        file.setPost(bookmarkList)

class BookmarkFragment : Fragment() {

    private lateinit var binding:FragmentBookmarkBinding
    private lateinit var bookmarkList: ArrayList<UrgentFdata>
    private lateinit var bookmarkAdapter: BookmarkAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        var toolbar: Toolbar = binding.toolbar
        val activity: AppCompatActivity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)

        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }




        bookmarkList = arrayListOf()
        Log.d("MyData", "${bookmarkList}")
        bookmarkList = arguments?.getSerializable("bookmarkedList") as ArrayList<UrgentFdata>




        bookmarkAdapter = BookmarkAdapter(bookmarkList, object : BookmarkAdapter.BooDonDetInterface{
            override fun onPress(booDonDet: UrgentFdata) {
                val bundle = bundleOf("donationData" to booDonDet)
                findNavController().navigate(R.id.donationDetailsFragment, bundle)
            }
        })

        binding.bookmarkRV.adapter = bookmarkAdapter

        binding.categoryRV.adapter = MainCategoryAdapter(MainCategory.values(), object : MainCategoryAdapter.MyCategoryInterface{
            override fun onItemClick(category: MainCategory, position: Int) {
                var categoryList = mutableListOf<UrgentFdata>()
                if (category.categoryName=="All"){
                    categoryList.addAll(bookmarkList)
                }
                bookmarkList.forEach{
                    if (it.category==category) categoryList.add(it)
                }
                bookmarkAdapter = BookmarkAdapter(categoryList, object : BookmarkAdapter.BooDonDetInterface{
                    override fun onPress(booDonDet: UrgentFdata) {
                        val bundle = bundleOf("donationData" to booDonDet)
                        findNavController().navigate(R.id.donationDetailsFragment, bundle)
                    }

                })
                binding.bookmarkRV.adapter = bookmarkAdapter
            }
        })

        binding.categoryRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.bookmarkRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }




}