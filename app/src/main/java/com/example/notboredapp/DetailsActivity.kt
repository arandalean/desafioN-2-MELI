package com.example.notboredapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notboredapp.databinding.ActivityDetailsBinding
import com.example.notboredapp.retrofit.APIService
import com.example.notboredapp.retrofit.SuggestResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val extra = intent.extras
        setContentView(binding.root)

        //val participants = extra?.getInt(participants)

        val type : String = extra?.getString("suggestion")!!
        val participants = 2

        if (participants != 0){
            searchDetailsWithPartipants(participants,type)
            binding.BTNTryAnother.setOnClickListener(){
                searchDetailsWithPartipants(participants,type)
            }
        } else {
            searchDetailsWithoutPartipants(type)
            binding.BTNTryAnother.setOnClickListener(){
                searchDetailsWithoutPartipants(type)
            }
        }

        binding.BTNBack.setOnClickListener(){
            finish()
        }

    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/api/activity/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun searchDetailsWithoutPartipants(type : String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getSuggestionByType(type)
            val suggestion : SuggestResponse? = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                    binding.TVSuggestTitle.text = suggestion?.type
                    binding.TVParticipantResult.text = (suggestion?.participants ?: 0).toString()
                    val priceResult = suggestion?.price
                    if (priceResult != null) {
                        when {
                            priceResult==0f -> binding.TVPriceResult.text = "Free"
                            (priceResult>0f && priceResult <= 0.3f) ->binding.TVPriceResult.text = "Low"
                            (priceResult>0.3f && priceResult <= 0.6f) ->binding.TVPriceResult.text = "Medium"
                            priceResult>0.6f -> "High".also { binding.TVPriceResult.text = it }

                        }
                    }
                    binding.TVSuggestDescription.text = suggestion?.activity
                } else {
                    showError()
                }
            }


        }
    }


    private fun searchDetailsWithPartipants(participants: Int, type : String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getSuggestionByTypeWithPart(participants,type)
            val suggestion : SuggestResponse? = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                    binding.TVSuggestTitle.text = suggestion?.type
                    binding.TVParticipantResult.text = (suggestion?.participants ?: 0).toString()
                    val priceResult = suggestion?.price
                    if (priceResult != null) {
                        when {
                            priceResult==0f -> binding.TVPriceResult.text = "Free"
                            (priceResult>0f && priceResult <= 0.3f) ->binding.TVPriceResult.text = "Low"
                            (priceResult>0.3f && priceResult <= 0.6f) ->binding.TVPriceResult.text = "Medium"
                            priceResult>0.6f -> "High".also { binding.TVPriceResult.text = it }

                        }
                    }
                    binding.TVSuggestDescription.text = suggestion?.activity

                } else {
                    showError()
                }
            }


        }
    }

    private fun showError() {
        Toast.makeText(this, "An error has happened", Toast.LENGTH_SHORT).show()
    }
}