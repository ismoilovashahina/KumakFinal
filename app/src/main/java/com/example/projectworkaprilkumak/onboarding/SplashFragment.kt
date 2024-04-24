package com.example.projectworkaprilkumak.onboarding

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.database.MyBase
import com.example.projectworkaprilkumak.databinding.FragmentSplashBinding
import com.example.projectworkaprilkumak.datas.MySortData
import com.example.projectworkaprilkumak.datas.MyUser


class SplashFragment : Fragment() {
    lateinit var myBase: MyBase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myBase = MyBase(requireContext())
//
//        val file = MySharedPreferences.getInstance(requireActivity())
//        val status:Boolean = file.getStatus()




        val binding = FragmentSplashBinding.inflate(inflater, container, false)
        var animation = AnimationUtils.loadAnimation(requireContext(), R.anim.logo_anim)
        binding.logo.startAnimation(animation)

        animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_anim)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                check()
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.loadingIcon.startAnimation(animation)

        return binding.root
    }

    fun check(){
        var myUser = MyUser()
        myBase.getUser().forEach {
            if (it.isSignedIn == "1"){
                myUser.id = it.id
                myUser.email = it.email
                myUser.password = it.password
                myUser.isSignedIn = it.isSignedIn
            }
        }
        if (myUser.isSignedIn == "1"){
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().popBackStack()
                findNavController().navigate(R.id.homeFragment)
            }, 3000)
        }else{
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().popBackStack()
                findNavController().navigate(R.id.signInFragment)
            }, 3000)
        }
        if (myBase.getAllSort().isEmpty()){
            myBase.addSort(MySortData("max"))
            // dastur yangi ishga tushganida fundlarni sortlashni eng kop fun bolganidan boshlaydi
        }
    }
    fun isExist(){

    }

}





