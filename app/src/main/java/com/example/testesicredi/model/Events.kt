package com.example.testesicredi.model

data class Events(
    var people: MutableList<String> = mutableListOf(),
    var date: Int = 0,
    var description: String = "",
    var image: String = "",
    var longitude: Double = 0.0,
    var latitude: Double = 0.0,
    var price: Double = 0.0,
    var title: String = "",
    var id: String = ""
)