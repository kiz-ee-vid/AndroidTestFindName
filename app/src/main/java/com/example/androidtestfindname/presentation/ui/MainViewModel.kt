package com.example.androidtestfindname.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtestfindname.data.RepositoryImpl
import com.example.androidtestfindname.data.room.NameDatabase
import com.example.androidtestfindname.data.room.Prediction
import com.example.androidtestfindname.presentation.di.App
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {

    private var db: NameDatabase = NameDatabase.getInstance(App.getContext()!!)
    private var nameDao = db.nameDao()

    val foundedAge = MutableLiveData<Prediction?>()

    var favouriteNames = ArrayList<Prediction>().toMutableList()
    var cachedRequests = ArrayList<Prediction>()

    init {
        CoroutineScope(Dispatchers.IO).launch() {
            if (nameDao != null) {
                favouriteNames = nameDao!!.getAll()
            } else throw Exception("Error, no database")
        }
    }

    fun getAge(name: String) {
        val cacheResult = checkCache(name)
        if (cacheResult == null)
            CoroutineScope(Dispatchers.IO).launch() {
                val data = repository.getAge(name)
                withContext(Dispatchers.Main) {
                    if (data != null) {
                        foundedAge.value = Prediction(data.name, data.age ?: 0)
                    }
                }
            }
        else foundedAge.value = Prediction(name, cacheResult)
    }

    fun addNameToFavourites() {
        val data = Prediction(foundedAge.value!!.name, foundedAge.value!!.age ?: 0)
        if (!favouriteNames.contains(data)) {
            favouriteNames.add(data)
            CoroutineScope(Dispatchers.IO).launch() {
                nameDao?.insert(data)
            }
        }
    }

    fun deleteChosen(chosenNames: ArrayList<Prediction>) {
        CoroutineScope(Dispatchers.IO).launch() {
            nameDao?.delete(chosenNames)
        }
        chosenNames.forEach {
            favouriteNames.remove(it)
        }
    }

    private fun checkCache(name: String): Byte? {
        cachedRequests.forEach {
            if (it.name == name)
                return it.age
        }
        return null
    }
}