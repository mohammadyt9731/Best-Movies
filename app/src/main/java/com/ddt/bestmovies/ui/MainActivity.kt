package com.ddt.bestmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ddt.bestmovies.R
import com.ddt.bestmovies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //binding
    private lateinit var binding:ActivityMainBinding
    //navController
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initViews
        binding.apply {

            navController=findNavController(R.id.navHost)
            bottomNav.setupWithNavController(navController)

            //onDestinationChangeListener
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if(destination.id==R.id.splashFragment)
                    bottomNav.visibility = View.GONE
                else
                    bottomNav.visibility = View.VISIBLE
            }


        }
    }

    override fun onNavigateUp(): Boolean {
        return  navController.navigateUp() || super.onNavigateUp()
    }
}