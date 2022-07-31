package com.ddt.bestmovies.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddt.bestmovies.databinding.FragmentSearchBinding
import com.ddt.bestmovies.ui.home.adapters.LastMoviesAdapter
import com.ddt.bestmovies.utils.initRecycler
import com.ddt.bestmovies.utils.setVisibility
import com.ddt.bestmovies.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentSearchBinding

    //adapter
    @Inject
    lateinit var searchAdapter: LastMoviesAdapter

    //other
    private val searchViewModel: SearchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //search
            edtSearch.addTextChangedListener {
                val search = it.toString()
                if (search.isNotEmpty()) {
                    searchViewModel.loadSearchMovies(search)
                }
            }

            //getMoviesList
            searchViewModel.searchMoviesList.observe(viewLifecycleOwner) {
                searchAdapter.setNewListDate(it.data)
                rvSearchList.initRecycler(LinearLayoutManager(requireContext()), searchAdapter)
            }

            //loading
            searchViewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    pbSearchLoading.setVisibility(true)
                } else {
                    pbSearchLoading.setVisibility(false)
                }
            }

            //empty
            searchViewModel.isEmptyList.observe(viewLifecycleOwner) {
                if (it) {
                    clEmptyItem.setVisibility(true)
                    rvSearchList.setVisibility(false)
                } else {
                    clEmptyItem.setVisibility(false)
                    rvSearchList.setVisibility(true)

                }
            }
        }
    }
}