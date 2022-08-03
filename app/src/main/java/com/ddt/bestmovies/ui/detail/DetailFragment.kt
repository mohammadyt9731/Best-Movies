package com.ddt.bestmovies.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ddt.bestmovies.R
import com.ddt.bestmovies.databinding.FragmentDetailBinding
import com.ddt.bestmovies.ui.favorite.FavoriteAdapter
import com.ddt.bestmovies.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    //binding
    private lateinit var binding:FragmentDetailBinding

    //adapter
    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    //other
    private val detailViewModel:DetailViewModel by viewModels()
    private var movieId=0;
    private val args:DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ///get data
        movieId=args.movieId
        //call api
        if(movieId>0){
            detailViewModel.loadMovieDetail(0)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //InitViews
        binding.apply {




        }
    }
}