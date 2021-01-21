package com.example.testesicredi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testesicredi.model.Events
import com.example.testesicredi.model.User
import com.example.testesicredi.repository.EventsRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class EventDetailViewModel(private val repository: EventsRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean>
        get() = _loading

    private val _successCall = MutableLiveData<Boolean>()

    val successCall: LiveData<Boolean>
        get() = _successCall

    private val _eventDetail = MutableLiveData<Events>()

    val eventDetail: LiveData<Events>
        get() = _eventDetail

    private val _error = MutableLiveData<HttpException>()

    val error: LiveData<HttpException>
        get() = _error

    fun getListEvents(eventId: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _eventDetail.value = repository.getEventDetail(eventId)
            } catch (error: HttpException) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    fun checkInEvent(user: User) {
        viewModelScope.launch {
            try {
                _loading.value = true
                repository.checkInEvent(user)
            } catch (error: HttpException) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }
}
