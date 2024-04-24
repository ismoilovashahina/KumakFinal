package com.example.projectworkaprilkumak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projectworkaprilkumak.Home.HomeFragment
import com.example.projectworkaprilkumak.actionbar.BookmarkFragment
import com.example.projectworkaprilkumak.actionbar.NotificationFragment
import com.example.projectworkaprilkumak.databinding.ActivityMainBinding
import com.example.projectworkaprilkumak.navbar.CalendarFragment
import com.example.projectworkaprilkumak.navbar.MyFundraisingFragment
import com.example.projectworkaprilkumak.navbar.ProfileFragment
import com.example.projectworkaprilkumak.onboarding.SplashFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = navHost.navController

        binding.navBar.setupWithNavController(navController)

        binding.navBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> navController.navigate(R.id.homeFragment)
                R.id.calendarFragment -> navController.navigate(R.id.calendarFragment)  //1
                R.id.myFundraisingFragment -> navController.navigate(R.id.myFundraisingFragment) //2
                R.id.chatFragment ->navController.navigate(R.id.chatFragment)//3
                R.id.profileFragment -> navController.navigate(R.id.profileFragment) //4
            }
            true
        }

        lifecycleScope.launchWhenResumed {

        navController.addOnDestinationChangedListener{_, destination,_ ->
            when(destination.id) {
                R.id.homeFragment -> showBottomNavBar()
                R.id.chatFragment -> showBottomNavBar()
                R.id.profileFragment -> showBottomNavBar()
                R.id.calendarFragment -> showBottomNavBar()
                R.id.myFundraisingFragment -> showBottomNavBar()
                else->hideBottomNavBar()
            }
        }
        }


}

    fun showBottomNavBar() {
        binding.navBar.visibility = View.VISIBLE
    }

    fun hideBottomNavBar() {
        binding.navBar.visibility = View.GONE
    }

}