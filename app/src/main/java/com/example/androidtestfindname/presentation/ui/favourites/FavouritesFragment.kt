package com.example.androidtestfindname.presentation.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidtestfindname.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment() {

    private val binding: FragmentFavouritesBinding by lazy { FragmentFavouritesBinding.inflate(layoutInflater) }
    private val viewModel: FavouritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }
}