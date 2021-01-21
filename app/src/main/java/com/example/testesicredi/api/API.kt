package com.example.testesicredi.api

import com.example.testesicredi.model.Events
import com.example.testesicredi.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface API {

    @GET("/api/events")
    suspend fun listEvents(): MutableList<Events>

    @GET("/api/events/{id}")
    suspend fun getEventDetail(@Path("id") id: String): Events

    @POST("/api/checkin")
    suspend fun sendEventCheckIn(@Body user: User): Boolean

}