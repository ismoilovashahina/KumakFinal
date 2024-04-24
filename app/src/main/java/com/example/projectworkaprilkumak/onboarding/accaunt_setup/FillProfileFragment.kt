package com.example.projectworkaprilkumak.onboarding.accaunt_setup

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.database.MyBase
import com.example.projectworkaprilkumak.databinding.FragmentFillProfileBinding
import com.example.projectworkaprilkumak.databinding.FragmentSignUpBinding
import com.example.projectworkaprilkumak.datas.ImageUser
import com.example.projectworkaprilkumak.datas.MyProfie
import com.example.projectworkaprilkumak.datas.Profile
import com.example.projectworkaprilkumak.datas.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class FillProfile : Fragment() {
    lateinit var myBase: MyBase
    lateinit var binding: FragmentFillProfileBinding
    lateinit var list:ArrayList<MyProfie>
    var readMyBytes:ByteArray? =null
    var absolutePath = ""
    private lateinit var gender:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myBase = MyBase(requireContext())
        list = ArrayList()
        binding = FragmentFillProfileBinding.inflate(inflater, container, false)
        var toolbar: Toolbar = binding.toolbar
        val activity : AppCompatActivity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)



        var list = listOf("male", "female")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, list)
        binding.dropDownValue.setAdapter(adapter)
        binding.dropDownValue.setOnItemClickListener { adapterView, view, i, l ->
            gender = list[i]
        }

        binding.continueBtn.setOnClickListener {
            if (binding.profileName.text!!.isNotEmpty() && binding.profileEmail.text!!.isNotEmpty() && binding.profilePhoneNumber.text!!.isNotEmpty() && binding.profileCity.text!!.isNotEmpty() && gender.isNotEmpty() && absolutePath.isNotEmpty() && readMyBytes!!.isNotEmpty()){
                myBase.addProile(MyProfie(getUserId(),binding.profileName.text.toString(),binding.profileEmail.text.toString(),binding.profilePhoneNumber.text.toString(),gender,binding.profileCity.text.toString(),absolutePath,readMyBytes))
                findNavController().navigate(R.id.selectInterestFragment)
            }else{
                Toast.makeText(requireContext(), "Not be empty!", Toast.LENGTH_SHORT).show()
            }
        }

        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        binding.btnSetImage.setOnClickListener {
            getNewImage()
        }

        return binding.root
    }
    fun getUserId():Int{
        return myBase.getUser().last().id!!
    }
    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()){
            it ?: return@registerForActivityResult
            binding.profileImage.setImageURI(it)
            val inputStream = requireContext().contentResolver?.openInputStream(it)
            val file = File(requireContext().filesDir,"image.jpg")
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream?.close()
            val absoulut = file.absolutePath

            val fileInputStream = FileInputStream(file)
            val readBytes = fileInputStream.readBytes()
            absolutePath = absoulut
            readMyBytes = readBytes
        }

    private fun getNewImage() {
        getImageContent.launch("image/*")
    }



}