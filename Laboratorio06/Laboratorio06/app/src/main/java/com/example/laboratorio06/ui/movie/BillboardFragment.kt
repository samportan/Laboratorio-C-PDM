package com.example.laboratorio06.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.laboratorio06.R
import com.example.laboratorio06.databinding.FragmentBillboardBinding


class BillboardFragment : Fragment() {


    private lateinit var binding: FragmentBillboardBinding
    private val viewModel: MovieViewModel by viewModels {
        MovieViewModel.factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_billboard, container, false)
        binding.actionButtonAdd.setOnClickListener {
            it.findNavController().navigate(R.id.action_billboardFragment_to_newMovieFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MovieAdapter {
            val bundle = Bundle().apply {
                putString("movieName", it.name)
                putString("movieCat", it.category)
                putString("movieDesc", it.description)
                putString("movieQ", it.qualification)
            }
            findNavController().navigate(R.id.action_billboardFragment_to_movieFragment, bundle)
        }
        adapter.submitData(viewModel.getMovies())

        binding.recylerViewMovies.adapter = adapter
    }


}