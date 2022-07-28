package com.ddt.bestmovies.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddt.bestmovies.R
import com.ddt.bestmovies.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    //binding
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentRegisterBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}