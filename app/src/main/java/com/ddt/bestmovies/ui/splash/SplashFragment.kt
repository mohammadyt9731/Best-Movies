package com.ddt.bestmovies.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.ddt.bestmovies.R
import com.ddt.bestmovies.databinding.FragmentSplashBinding
import com.ddt.bestmovies.utils.Constants
import com.ddt.bestmovies.utils.UserDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentSplashBinding
    //dataSore
    @Inject
    lateinit var userDataStore: UserDataStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentSplashBinding.inflate(layoutInflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set delay
        lifecycle.coroutineScope.launchWhenCreated {
            delay(Constants.SPLASH_DELAY_DURATION)

            //check user token
            userDataStore.getUserToken().collect(){
                if(it.isEmpty())
                    findNavController().navigate(R.id.actionSplashToRegister)
                else
                    findNavController().navigate(R.id.actionToHome)

            }

        }
    }

}