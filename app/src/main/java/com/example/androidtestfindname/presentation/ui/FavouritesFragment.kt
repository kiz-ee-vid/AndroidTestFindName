package com.example.androidtestfindname.presentation.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtestfindname.R
import com.example.androidtestfindname.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment() {

    private val binding: FragmentFavouritesBinding by lazy {
        FragmentFavouritesBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var favouritesAdapter: FavouritesAdapter
    private val productRecycler: RecyclerView by lazy { binding.recyclerNames }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dialog = makeDialog()
        favouritesAdapter = FavouritesAdapter() {
            if (it)
                binding.buttonDelete.visibility = VISIBLE
            else
                binding.buttonDelete.visibility = GONE
        }
        favouritesAdapter.addList(viewModel.favouriteNames)
        productRecycler.adapter = favouritesAdapter
        productRecycler.layoutManager = LinearLayoutManager(context)

        binding.buttonDelete.setOnClickListener {
            dialog.show()
        }

        return binding.root
    }

    private fun makeDialog(): Dialog {
        val builder = AlertDialog.Builder(binding.root.context, R.style.AlertDialog)
            .setTitle("Удалить Имя")
            .setMessage("Вы уверены что хотите удалить выбранное имя из избранных?")
            .setPositiveButton(
                R.string.yes
            ) { _, _ ->
                val chosenNames = favouritesAdapter.getChosen()
                viewModel.deleteChosen(chosenNames)
                favouritesAdapter.addList(viewModel.favouriteNames)
            }
            .setNeutralButton(
                R.string.no
            ) { _, _ -> }
        return builder.create()
    }
}