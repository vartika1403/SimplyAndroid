package com.example.appnetworking

import androidx.lifecycle.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class QuoteViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _countState = MutableStateFlow(0)
    val countState:StateFlow<Int> = _countState
    val quotes :LiveData<List<Quote>?> = MutableLiveData()

    fun increamentState() {
        _countState.value++
    }

    fun decreamentState() {
        _countState.value--
    }

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}