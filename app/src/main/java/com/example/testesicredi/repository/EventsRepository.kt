package com.example.testesicredi.repository

import com.example.testesicredi.api.API
import com.example.testesicredi.model.User

class EventsRepository(private val api: API) {

    suspend fun getEventList() = api.listEvents()
    suspend fun getEventDetail(eventId: String) = api.getEventDetail(eventId)
    suspend fun checkInEvent(user: User) = api.sendEventCheckIn(user)

}
