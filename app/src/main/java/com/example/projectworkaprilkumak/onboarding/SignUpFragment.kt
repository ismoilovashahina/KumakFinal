package com.example.projectworkaprilkumak.onboarding

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.database.MyBase
import com.example.projectworkaprilkumak.databinding.FragmentSignUpBinding
import com.example.projectworkaprilkumak.datas.MyUser
import com.example.projectworkaprilkumak.datas.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SignUpFragment : Fragment() {
    lateinit var myBase: MyBase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myBase = MyBase(requireContext())
        val binding = FragmentSignUpBinding.inflate(inflater,container,false)
        binding.signupBtn.setOnClickListener {
            myBase.addUser(MyUser(binding.signupEmail.text.toString(),binding.signupPassword.text.toString(),"0"))
            findNavController().navigate(R.id.selectCountryFragment)
        }

        binding.signinTv.setOnClickListener { findNavController().navigate(R.id.signInFragment) }

        return binding.root

    }
    /*
    if (users == ""){
                myBase.addUser(MyUser(binding.signupEmail.text.toString(),binding.signupPassword.text.toString(),"0"))
                val str = gson.toJson(userList)
                edit.putString("users", str).commit()
            } else{

                }
                if (pos==true){
                    myBase.addUser(MyUser(binding.signupEmail.text.toString(),binding.signupPassword.text.toString(),"0"))

                    edit.putString("users", str).commit()
                    findNavController().navigate(R.id.selectCountryFragment)
                } else{
                    myBase.addUser(MyUser(binding.signupEmail.text.toString(),binding.signupPassword.text.toString(),"0"))
                    Toast.makeText(requireContext(), "Change inputs", Toast.LENGTH_SHORT).show()
                }
            }
     */

}