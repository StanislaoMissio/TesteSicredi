package com.example.testesicredi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testesicredi.repository.EventsRepository
import com.example.testesicredi.model.Events
import com.example.testesicredi.util.Utils
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ListEventsViewModel(private val eventsRepository: EventsRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean>
        get() = _loading

    private val _listEvents = MutableLiveData<MutableList<Events>>()

    val listEvents: LiveData<MutableList<Events>>
        get() = _listEvents

    private val _error = MutableLiveData<HttpException>()

    val error: LiveData<HttpException>
        get() = _error

    fun getListEvents() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _listEvents.value = eventsRepository.getEventList()
            } catch(error: HttpException) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

}