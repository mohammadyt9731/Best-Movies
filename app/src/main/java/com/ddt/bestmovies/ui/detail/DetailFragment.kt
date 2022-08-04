package com.ddt.bestmovies.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.ddt.bestmovies.R
import com.ddt.bestmovies.databinding.FragmentDetailBinding
import com.ddt.bestmovies.db.MovieEntity
import com.ddt.bestmovies.ui.favorite.FavoriteAdapter
import com.ddt.bestmovies.utils.Constants
import com.ddt.bestmovies.utils.initRecycler
import com.ddt.bestmovies.utils.setVisibility
import com.ddt.bestmovies.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    //binding
    private lateinit var binding:FragmentDetailBinding

    //adapter
    @Inject
    lateinit var detailImageAdapter: DetailImageAdapter

    //other
    private val detailViewModel:DetailViewModel by viewModels()
    private var movieId=0;
    private val args:DetailFragmentArgs by navArgs()

    //entity
    @Inject
    lateinit var movieEntity: MovieEntity


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
            detailViewModel.loadMovieDetail(movieId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //InitViews
        binding.apply {
            //load data
            detailViewModel.movieDetail.observe(viewLifecycleOwner){response->

                ivBigPoster.load(response.poster)
                ivNormalPoster.load(response.poster){
                    crossfade(true)
                    crossfade(Constants.LOAD_IMAGE_CROSS_FADE)
                }

                tvMovieName.text=response.title
                tvRate.text=response.imdbRating
                tvTime.text=response.runtime
                tvDate.text=response.released
                tvSummaryInfo.text=response.plot
                tvActorsInfo.text=response.actors

                //images adapter
                detailImageAdapter.differ.submitList(response.images)
                rvMovieImages.initRecycler(
                    LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                    ,detailImageAdapter
                )

                //fav click
                ivFavorite.setOnClickListener{
                    movieEntity.id=movieId
                    movieEntity.poster=response.poster.toString()
                    movieEntity.title=response.title.toString()
                    movieEntity.rate=response.rated.toString()
                    movieEntity.country=response.country.toString()
                    movieEntity.year=response.year.toString()

                    detailViewModel.favoriteMovie(movieId,movieEntity)


                }

            }

            //loading
            detailViewModel.loading.observe(viewLifecycleOwner){
                if(it){
                    pbDetailLoading.setVisibility(true)
                    detailScrollView.setVisibility(false)
                }else{
                    pbDetailLoading.setVisibility(false)
                    detailScrollView.setVisibility(true)

                }
            }

            //fav icon color
            lifecycleScope.launchWhenCreated {
                if(detailViewModel.existMovie(movieId)){
                    ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(),R.color.scarlet))
                }else{
                    ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(),R.color.philippineSilver))

                }
            }

            //change icon
            detailViewModel.isFavorite.observe(viewLifecycleOwner){
                if(it){
                    ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(),R.color.scarlet))
                }else{
                    ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(),R.color.philippineSilver))

                }
            }

            //back click
            ivBack.setOnClickListener(){
                findNavController().navigateUp()
            }




        }
    }
}