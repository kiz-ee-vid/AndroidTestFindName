package com.example.androidtestfindname.presentation.ui.main

import androidx.lifecycle.ViewModel
import com.example.androidtestfindname.data.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {


}