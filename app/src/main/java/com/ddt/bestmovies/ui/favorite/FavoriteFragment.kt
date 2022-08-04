package com.ddt.bestmovies.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddt.bestmovies.R
import com.ddt.bestmovies.databinding.FragmentFavoriteBinding
import com.ddt.bestmovies.ui.home.HomeFragmentDirections
import com.ddt.bestmovies.utils.initRecycler
import com.ddt.bestmovies.utils.setVisibility
import com.ddt.bestmovies.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentFavoriteBinding

    //adapter
    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    //other
    private val favoriteViewModel:FavoriteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //Show all favorite
            favoriteViewModel.loadFavoriteList()
            //List
            favoriteViewModel.favoriteList.observe(viewLifecycleOwner) {
                favoriteAdapter.setNewListDate(it)
                rvFavoriteList.initRecycler(LinearLayoutManager(requireContext()), favoriteAdapter)
            }

            //click
            favoriteAdapter.setOnItemClickListener {
                val direction= FavoriteFragmentDirections.actionToDetail(it.id!!.toInt())
                findNavController().navigate(direction)
            }

            //empty
            favoriteViewModel.isEmptyList.observe(viewLifecycleOwner) {
                if (it) {
                    clEmptyItem.setVisibility(true)
                    rvFavoriteList.setVisibility(false)
                } else {
                    clEmptyItem.setVisibility(false)
                    rvFavoriteList.setVisibility(true)

                }
            }
        }
    }
}