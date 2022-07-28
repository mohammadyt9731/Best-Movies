package com.ddt.bestmovies.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddt.bestmovies.R
import com.ddt.bestmovies.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentSplashBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}