package com.example.androidtestfindname.presentation.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtestfindname.data.room.Name
import com.example.androidtestfindname.databinding.ItemFavouriteBinding

class FavouritesAdapter(var itemClick: (Boolean) -> Unit) :
    RecyclerView.Adapter<FavouritesAdapter.MenuHolder>() {

    private var namesList = ArrayList<Name>()
    private var withCheckbox = false
    private var showDeleteButton = false
    var checkedNum = 0
    private var chosenNames = ArrayList<String>()

    inner class MenuHolder(val binding: ItemFavouriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val binding = ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        with(holder.binding) {
            textFavouriteName.text = namesList[position].name
            if (withCheckbox) checkBox.visibility = VISIBLE
            else checkBox.visibility = INVISIBLE
            root.setOnLongClickListener {
                withCheckbox = withCheckbox.not()
                if (showDeleteButton){
                    showDeleteButton = false
                    itemClick(showDeleteButton)
                }
                notifyDataSetChanged()
                true
            }
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedNum++
                    chosenNames.add(namesList[position].name)
                }
                else{
                    checkedNum--
                    chosenNames.remove(namesList[position].name)
                }
                showDeleteButton = checkedNum > 0
                itemClick(showDeleteButton)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(names: List<Name>) {
        namesList.clear()
        namesList.addAll(names)
        resetSelection()
        notifyDataSetChanged()
    }

    private fun resetSelection() {
        showDeleteButton = false
        chosenNames.clear()
        checkedNum = 0
        withCheckbox = false
        itemClick(showDeleteButton)
    }

    override fun getItemCount(): Int {
        return namesList.size
    }

    fun getChosen(): List<String> {
        return chosenNames
    }
}