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


        val participants = extra?.getString("participants")?.toInt()
        val type = extra?.getString("suggestion")


        if (participants != 0){
            searchDetailsWithParticipants("?participants=$participants&type=$type")
            binding.BTNTryAnother.setOnClickListener(){
                searchDetailsWithParticipants("?participants=$participants&type=$type")
            }
        } else {
            searchDetailsWithoutParticipants("?type=$type")
            binding.BTNTryAnother.setOnClickListener(){
                searchDetailsWithoutParticipants("?type=$type")
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

    private fun searchDetailsWithoutParticipants(url: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getSuggestionByType(url)
            val suggestion : SuggestResponse? = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                    binding.TVSuggestTitle.text = suggestion?.type
                    binding.TVParticipantResult.text = (suggestion?.participants ?: 0).toString()
                    val priceResult = suggestion?.price
                    priceResult.let {
                        when {
                            it==0f -> binding.TVPriceResult.text = "Free"
                            (it!!>0f && it <= 0.3f) ->binding.TVPriceResult.text = "Low"
                            (it>0.3f && it <= 0.6f) ->binding.TVPriceResult.text = "Medium"
                            else -> binding.TVPriceResult.text = "High"
                        }
                    }
                    binding.TVSuggestDescription.text = suggestion?.activity

                    suggestion?.error?.let { showError(it) }
                } else {
                    showError(R.string.unsuccessfull.toString())
                }
            }


        }
    }


    private fun searchDetailsWithParticipants(url : String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getSuggestionByTypeWithPart(url)
            val suggestion : SuggestResponse? = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                    binding.TVSuggestTitle.text = suggestion?.type
                    binding.TVParticipantResult.text = (suggestion?.participants ?: 0).toString()
                    val priceResult = suggestion?.price
                    priceResult.let {
                        when {
                            it==0f -> binding.TVPriceResult.text = "Free"
                            (it!!>0f && it <= 0.3f) ->binding.TVPriceResult.text = "Low"
                            (it>0.3f && it <= 0.6f) ->binding.TVPriceResult.text = "Medium"
                            else -> binding.TVPriceResult.text = "High"
                        }
                    }
                    binding.TVSuggestDescription.text = suggestion?.activity

                    suggestion?.error?.let { showError(it) }

                }else {
                    showError(R.string.unsuccessfull.toString())
                }
            }


        }
    }

    private fun showError(error: String) {
        Toast.makeText(this, "$error", Toast.LENGTH_SHORT).show()
    }
}