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
import com.example.projectworkaprilkumak.databinding.FragmentSignInBinding
import com.example.projectworkaprilkumak.datas.MyUser
import com.example.projectworkaprilkumak.datas.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SignInFragment : Fragment() {
    var hasGot = false
    lateinit var myBase: MyBase
    lateinit var userList:ArrayList<MyUser>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myBase = MyBase(requireContext())
        userList = ArrayList()
        userList.addAll(myBase.getUser())
        val binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.signinBtn.setOnClickListener {
                userList.forEach {
                    if (binding.signinEmail.text.toString() == it.email && binding.signinPassword.text.toString() == it.password){
                        hasGot = true
                        myBase.editUser(MyUser(it.id,it.email,it.password,"1"))
                    }else{
                        hasGot = false
                    }
                }
                if (hasGot){
                    findNavController().navigate(R.id.homeFragment)
                }else Toast.makeText(requireContext(), "Email or Password isn't correct!", Toast.LENGTH_SHORT).show()
            
        }

        binding.signupTv.setOnClickListener {findNavController().navigate(R.id.signUpFragment)}

        return binding.root
    }
}

/*

        var users = sharedPreferences.getString("users", "")
            var pos = false

            if(users == ""){
                Toast.makeText(requireContext(), "Entered empty blanks!", Toast.LENGTH_SHORT).show()
            } else{
                userList = gson.fromJson(users, type)
                for(i in userList){
                    if (i.password == binding.signinPassword.text.toString() && i.email == binding.signinEmail.text.toString()){
                        pos = true
                        break
                    } else{
                        pos = false
                    }
                }

                if(pos==true) findNavController().navigate(R.id.homeFragment)
                else Toast.makeText(requireContext(), "You have not registered yet!", Toast.LENGTH_SHORT).show()
            }
         */