package com.example.notboredapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notboredapp.databinding.ActivityDetailsBinding
import com.example.notboredapp.databinding.ActivityMainBinding
import com.example.notboredapp.databinding.ActivityRandomBinding
import com.example.notboredapp.databinding.ActivityRandomBinding.*
import com.example.notboredapp.retrofit.APIService
import com.example.notboredapp.retrofit.SuggestResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomActivity : AppCompatActivity() {

    var type : String? = ""
    var participants : Int? = 0
    var price : Float? = 0f
    var description : String? = ""

    private lateinit var binding: ActivityRandomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        //val extra = intent.extras
        setContentView(binding.root)

        //val participants = extra?.getString(PARTICIPANTS)
        searchRandom()



    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/api/activity/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun searchRandom(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getSuggestionRandom()
            val suggestion : SuggestResponse? = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                    binding.TVTypeR.text = suggestion?.type
                    binding.TVParticipantResultR.text = (suggestion?.participants ?: 0).toString()
                    binding.TVPriceResultR.text = suggestion?.price.toString()
                    binding.TVSuggestDescriptionR.text = suggestion?.activity
                    // Llamar a funcion con Random Activity
                    //showRandom(type, participants!!,price, description)
                } else {
                    showError()
                }
            }


        }
    }

    private fun showRandom(type: String?, participants: Int, price: Float?, description: String?) {
        TODO("Not yet implemented")
    }

    private fun showError() {
        Toast.makeText(this, "An error has happened", Toast.LENGTH_SHORT).show()
    }
}