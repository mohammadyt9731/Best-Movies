package com.ddt.bestmovies.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ddt.bestmovies.R
import com.ddt.bestmovies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityMainBinding

    //navController
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initViews
        binding.apply {

            navController = findNavController(R.id.navHost)
            bottomNav.setupWithNavController(navController)

            //onDestinationChangeListener
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.splashFragment || destination.id == R.id.registerFragment || destination.id == R.id.detailFragment)
                    bottomNav.visibility = View.GONE
                else
                    bottomNav.visibility = View.VISIBLE
            }


        }
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {

            R.id.homeFragment, R.id.searchFragment, R.id.favoriteFragment -> {
                finish()
            }

            else -> navController.navigateUp()

        }


    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}