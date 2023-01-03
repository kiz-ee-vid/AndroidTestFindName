package com.example.androidtestfindname.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.androidtestfindname.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val binding: FragmentMainBinding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.searchView.isIconifiedByDefault = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getAge(query)
                return false
            }
        })

        viewModel.foundedAge.observe(viewLifecycleOwner) {
            binding.frameText.visibility = GONE
            binding.frameAge.visibility = VISIBLE
            binding.buttonAddToFavourite.visibility = VISIBLE
            binding.buttonShare.visibility = VISIBLE
            if (it != null) {
                binding.age.text = it.age.toString()
            }
        }

        binding.buttonAddToFavourite.setOnClickListener {
            viewModel.addNameToFavourites()
        }

        binding.buttonShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Predicted age of ${viewModel.foundedAge.value?.name} is ${viewModel.foundedAge.value?.age}")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }

        return binding.root
    }
}