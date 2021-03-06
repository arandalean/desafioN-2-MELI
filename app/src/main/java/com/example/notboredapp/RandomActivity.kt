package com.example.notboredapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notboredapp.databinding.ActivityRandomBinding
import com.example.notboredapp.retrofit.APIService
import com.example.notboredapp.retrofit.SuggestResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRandomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val extra = intent.extras
        var participants = extra?.getString("participants")?.toInt()


        if (participants !=0){
            searchRandomWithParticipants("?participants=$participants")
            binding.BTNTryAnotherR.setOnClickListener(){
                searchRandomWithParticipants("?participants=$participants")
            }
        } else{
            searchRandom("")
            binding.BTNTryAnotherR.setOnClickListener(){
                searchRandom("")
            }
        }


        binding.BTNBackR.setOnClickListener(){
            finish()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/api/activity/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }



    private fun searchRandomWithParticipants(url:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getSuggestionRandomWithPart(url)
            val suggestion : SuggestResponse? = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                        binding.TVTypeR.text = suggestion?.type
                        binding.TVParticipantResultR.text =
                            (suggestion?.participants ?: 0).toString()
                        val priceResult = suggestion?.price
                        priceResult.let {
                            when {
                                it==0f -> binding.TVPriceResultR.text = "Free"
                                (it!!>0f && it <= 0.3f) ->binding.TVPriceResultR.text = "Low"
                                (it>0.3f && it <= 0.6f) ->binding.TVPriceResultR.text = "Medium"
                                else -> binding.TVPriceResultR.text = "High"
                            }
                        }
                        binding.TVSuggestDescriptionR.text = suggestion?.activity

                    suggestion?.error?.let { showError(it) }
                }else {
                    showError(R.string.unsuccessfull.toString())
                }
            }


        }
    }

    private fun searchRandom(url: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getSuggestionRandom(url)
            val suggestion : SuggestResponse? = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                    binding.TVTypeR.text = suggestion?.type
                    binding.TVParticipantResultR.text = suggestion?.participants.toString()
                    val priceResult = suggestion?.price
                    priceResult.let {
                        when {
                            it==0f -> binding.TVPriceResultR.text = "Free"
                            (it!!>0f && it <= 0.3f) ->binding.TVPriceResultR.text = "Low"
                            (it>0.3f && it <= 0.6f) ->binding.TVPriceResultR.text = "Medium"
                            else -> binding.TVPriceResultR.text = "High"
                        }
                    }

                    binding.TVSuggestDescriptionR.text = suggestion?.activity

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