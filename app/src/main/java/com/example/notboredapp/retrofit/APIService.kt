package com.example.notboredapp.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getSuggestionByType(@Url url:String) :Response<SuggestResponse>
    suspend fun getSuggestionByTypeWithPart(@Url url:String):Response<SuggestResponse>
    @GET ("http://www.boredapi.com/api/activity/")
    suspend fun getSuggestionRandom():Response<SuggestResponse>
    suspend fun getSuggestionRandomWithPart(@Url url: String):Response<SuggestResponse>



}