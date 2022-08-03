package com.ddt.bestmovies.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ddt.bestmovies.databinding.FragmentHomeBinding
import com.ddt.bestmovies.ui.home.adapters.GenresListAdapter
import com.ddt.bestmovies.ui.home.adapters.LastMoviesAdapter
import com.ddt.bestmovies.ui.home.adapters.TopMoviesAdapter
import com.ddt.bestmovies.utils.Constants
import com.ddt.bestmovies.utils.initRecycler
import com.ddt.bestmovies.utils.setVisibility
import com.ddt.bestmovies.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentHomeBinding

    //adapter
    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter

    @Inject
    lateinit var gernresListAdapter: GenresListAdapter

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter

    //viewModel
    private val homeViewModel:HomeViewModel by viewModels()
    //pager snap helper
    private val pagerSnapHelper:PagerSnapHelper by lazy { PagerSnapHelper() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //call api
        homeViewModel.loadTopMoviesList(Constants.GENRE_ID)
        homeViewModel.loadGenresList()
        homeViewModel.loadLastMoviesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initViews
        binding.apply {
            //TopMovies
            homeViewModel.topMoviesList.observe(viewLifecycleOwner){
                topMoviesAdapter.differ.submitList(it.data)

                //recyclerView
                rvTopMovies.initRecycler(LinearLayoutManager(requireContext()
                    ,LinearLayoutManager.HORIZONTAL,false)
                    ,topMoviesAdapter)

                //indicator
                pagerSnapHelper.attachToRecyclerView(rvTopMovies)
                indicator.attachToRecyclerView(rvTopMovies,pagerSnapHelper)
            }

            //Genres
            homeViewModel.genresList.observe(viewLifecycleOwner){
                gernresListAdapter.differ.submitList(it)

                rvGenre.initRecycler(LinearLayoutManager(requireContext()
                    ,LinearLayoutManager.HORIZONTAL
                    ,false)
                    ,gernresListAdapter)
            }

            //LastMovies
            homeViewModel.lastMoviesList.observe(viewLifecycleOwner){
                lastMoviesAdapter.setNewListDate(it.data)

                //recyclerView
                rvLastMovies.initRecycler(LinearLayoutManager(requireContext()),lastMoviesAdapter)
            }

            //click
            lastMoviesAdapter.setOnItemClickListener {
                val direction=HomeFragmentDirections.actionToDetail(it.id!!.toInt())
                findNavController().navigate(direction)
            }

            //loading
            homeViewModel.loading.observe(viewLifecycleOwner){

                if(it){

                    nestedScrollHome.setVisibility(false)
                    progressBarLoading.setVisibility(true)
                }else{

                    nestedScrollHome.setVisibility(true)
                    progressBarLoading.setVisibility(false)
                }
            }
        }
    }

}