package com.udacity.sodastore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.sodastore.models.Soda

class SodaViewModel : ViewModel() {

    // So this is the soda list and we are putting it in the viewmodel
    private val _sodaList = MutableLiveData<ArrayList<Soda>>()
    val sodaList: LiveData<ArrayList<Soda>>
        get() = _sodaList

    // Do this to initialize the sodaList for the first time
    init {
        _sodaList.value = ArrayList()
    }

    // This function is returning the current value of the sodaList and then
    // I'm using add to add the new soda object to it.
    // So we need to put this addSoda function into the button of our SodaDetailFragment
    fun addSoda(soda: Soda) {
        _sodaList.value?.add(soda)
    }
}

