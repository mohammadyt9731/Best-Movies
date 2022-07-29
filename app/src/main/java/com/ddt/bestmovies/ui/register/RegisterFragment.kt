package com.ddt.bestmovies.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ddt.bestmovies.R
import com.ddt.bestmovies.databinding.FragmentRegisterBinding
import com.ddt.bestmovies.models.register.BodyRegister
import com.ddt.bestmovies.utils.UserDataStore
import com.ddt.bestmovies.utils.setVisibility
import com.ddt.bestmovies.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    //binding
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentRegisterBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    @Inject
    lateinit var userDataStore: UserDataStore

    @Inject
    lateinit var bodyRegister: BodyRegister

    private val registerViewModel:RegisterViewModel by viewModels()




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initViews
        binding.apply {
            //click
            btnSubmit.setOnClickListener{

                val name=etName.text.toString()
                val email=etEmail.text.toString()
                val password=etPassword.text.toString()
                //validation
                if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){

                    bodyRegister.name=name
                    bodyRegister.email=email
                    bodyRegister.password=password

                }else
                {
                    Snackbar.make(it,getString(R.string.fillAllFields),Snackbar.LENGTH_LONG).show()
                }

                //set data
                registerViewModel.registerUser(bodyRegister)

                //loading
                registerViewModel.loading.observe(viewLifecycleOwner){ isShown->
                    if(isShown){
                        progressBarLoading.setVisibility(true)
                        btnSubmit.setVisibility(false)

                    }else{
                        progressBarLoading.setVisibility(false)
                        btnSubmit.setVisibility(true)

                    }

                }

                //register
                registerViewModel.responseRegister.observe(viewLifecycleOwner){response->

                    lifecycleScope.launchWhenCreated {
                        userDataStore.saveUserToken(response.name.toString())
                    }


                }


            }



        }
    }
}