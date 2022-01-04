package com.example.notboredapp.retrofit

data class SuggestResponse(
    var activity: String,
    var type: String,
    var participants: Int,
    var price: Float,
    var link: String,
    var key: String,
    var accessibility: String,
    var error: String
)