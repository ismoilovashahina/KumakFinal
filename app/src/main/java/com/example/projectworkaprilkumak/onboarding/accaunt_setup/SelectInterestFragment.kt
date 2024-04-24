package com.example.projectworkaprilkumak.onboarding.accaunt_setup

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
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.adapters.SelectInterestAdapter
import com.example.projectworkaprilkumak.database.MyBase
import com.example.projectworkaprilkumak.databinding.FragmentSelectInterestBinding
import com.example.projectworkaprilkumak.datas.MySelectedInterest

import com.example.projectworkaprilkumak.datas.SelectInterest
import com.example.projectworkaprilkumak.modules.MyData
import com.google.gson.Gson


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SelectInterestFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var interestS = ""
    lateinit var list:ArrayList<SelectInterest>
    lateinit var myBase: MyBase
    private lateinit var interestList: MutableList<SelectInterest>
    private lateinit var interestAdapter: SelectInterestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectInterestBinding.inflate(inflater, container, false)
        var toolbar: Toolbar = binding.toolbar
        val activity : AppCompatActivity = getActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)

        myBase = MyBase(requireContext())
        list = ArrayList()
        toolbar.setNavigationOnClickListener { findNavController().navigate(R.id.fillProfile) }


        interestList = mutableListOf()

        interestList.add(SelectInterest(R.drawable.education_icon, "Education"))
        interestList.add(SelectInterest(R.drawable.environment_ic, "Environment"))
        interestList.add(SelectInterest(R.drawable.social_ic, "Social"))
        interestList.add(SelectInterest(R.drawable.sick_child_ic, "Sick child"))
        interestList.add(SelectInterest(R.drawable.medical_ic, "Medical"))
        interestList.add(SelectInterest(R.drawable.infrastructure_ic, "Infrastructure"))
        interestList.add(SelectInterest(R.drawable.art_ic, "Art"))
        interestList.add(SelectInterest(R.drawable.disable_ic, "Disaster"))
        interestList.add(SelectInterest(R.drawable.orphanage_ic, "Orphanage"))
        interestList.add(SelectInterest(R.drawable.disable_ic, "Disable"))
        interestList.add(SelectInterest(R.drawable.humanity_ic, "Humanity"))
        interestList.add(SelectInterest(R.drawable.others_ic, "Others"))


        interestAdapter = SelectInterestAdapter(interestList, object : SelectInterestAdapter.InterestInterface{
            override fun onInterestClick(interest: SelectInterest, position: Int) {
                //val bundle = bundleOf("interest" to interest)
                list.add(interest)
            }

        })

        binding.interestRV.layoutManager = GridLayoutManager(context, 3)
        binding.interestRV.adapter = interestAdapter


        binding.continueBtn.setOnClickListener {
            if (list.isNotEmpty()){
                val json = Gson()
                val stringList = json.toJson(list)
                myBase.addInterest(MySelectedInterest(stringList))
                findNavController().navigate(R.id.createPinFragment)
            }else{
                findNavController().navigate(R.id.createPinFragment)
            }

        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectInterestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}