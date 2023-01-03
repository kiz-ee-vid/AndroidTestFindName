package com.example.androidtestfindname.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.androidtestfindname.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val binding: FragmentMainBinding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels()

    val pattern = "[A-Za-z]+".toRegex()

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
                if (!query.matches(pattern)) {
                    Toast.makeText(context, "Use only Latin characters", Toast.LENGTH_SHORT).show()
                    return true
                }
                if (query.length > 15) {
                    Toast.makeText(context, "The name is too long", Toast.LENGTH_SHORT).show()
                    return true
                }
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