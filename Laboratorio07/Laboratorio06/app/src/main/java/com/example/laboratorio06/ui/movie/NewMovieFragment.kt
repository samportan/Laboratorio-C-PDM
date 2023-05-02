package com.example.laboratorio06.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.laboratorio06.R
import com.example.laboratorio06.data.model.MovieModel
import com.example.laboratorio06.databinding.FragmentNewMovieBinding
import com.example.laboratorio06.ui.movie.MovieViewModel.Companion.APP_TAG


class NewMovieFragment : Fragment() {

    private lateinit var binding: FragmentNewMovieBinding
    private val viewModel: MovieViewModel by viewModels {
        MovieViewModel.factory
    }
    private var onBackPressedCallback: OnBackPressedCallback? = null
    private fun setViewModel() {
        binding.viewmodel = viewModel
    }

    private fun observerStatus(){
        viewModel.status.observe(viewLifecycleOwner) { status ->
            when{
                status.equals(MovieViewModel.WRONG_INFORMATION) -> {
                    Log.d(APP_TAG, status)
                    Toast.makeText(this.activity, "WRONG INFORMATION", Toast.LENGTH_SHORT).show()
                    viewModel.clearStatus()
                }
                status.equals(MovieViewModel.MOVIE_CREATED) -> {
                    Log.d(APP_TAG, status)
                    Log.d(APP_TAG, viewModel.getMovies().toString())
                    Toast.makeText(this.activity, "MOVIE CREATED", Toast.LENGTH_SHORT).show()

                    viewModel.clearStatus()
                    findNavController().popBackStack()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_movie, container, false)
        setViewModel()
        observerStatus()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback as OnBackPressedCallback
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback?.remove()
        onBackPressedCallback = null
    }

}