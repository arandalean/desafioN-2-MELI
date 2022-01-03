package com.example.notboredapp.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {
    @GET ("http://www.boredapi.com/api/activity/")
    suspend fun getSuggestionByType(@Query ("type") type:String) :Response<SuggestResponse>
    @GET ("http://www.boredapi.com/api/activity/")
    suspend fun getSuggestionByTypeWithPart(@Query ("participants") participanst : Int,
                                            @Query ("type") type : String):Response<SuggestResponse>
    @GET ("http://www.boredapi.com/api/activity/")
    suspend fun getSuggestionRandom():Response<SuggestResponse>
    @GET ("http://www.boredapi.com/api/activity/")
    suspend fun getSuggestionRandomWithPart(@Query ("participants") participanst : Int):Response<SuggestResponse>



}