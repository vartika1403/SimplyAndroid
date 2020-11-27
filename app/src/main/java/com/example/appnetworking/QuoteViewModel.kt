package com.example.appnetworking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class QuoteViewModel : ViewModel() {

    private val _countState = MutableStateFlow(0)
    val countState:StateFlow<Int> = _countState
    val quotes :LiveData<List<Quote>?> = MutableLiveData()

    fun increamentState() {
        _countState.value++
    }

    fun decreamentState() {
        _countState.value--
    }
    init {
       // viewModelScope.la
    }
}