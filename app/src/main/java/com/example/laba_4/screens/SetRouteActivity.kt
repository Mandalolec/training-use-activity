package com.example.laba_4.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.laba_4.R
import com.example.laba_4.databinding.ActivitySetRouteBinding

class SetRouteActivity : AppCompatActivity() {
    lateinit var binding: ActivitySetRouteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClickGoMain(view: View){
        if(binding.startStreetEditText.text.isEmpty()
            || binding.startHouseEditText.text.isEmpty()
            || binding.startFlatEditText.text.isEmpty()
            || binding.endStreetEditText.text.isEmpty()
            || binding.endHouseEditText.text.isEmpty()
            || binding.endFlatEditText.text.isEmpty()){
            Toast.makeText(applicationContext, "Мало данных ", Toast.LENGTH_SHORT).show()
        }else{
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("startStreet", binding.startStreetEditText.text.toString())
            i.putExtra("startHouse", binding.startHouseEditText.text.toString())
            i.putExtra("startFlat", binding.startFlatEditText.text.toString())

            i.putExtra("endStreet", binding.endStreetEditText.text.toString())
            i.putExtra("endHouse", binding.endHouseEditText.text.toString())
            i.putExtra("endFlat", binding.endFlatEditText.text.toString())

            i.putExtra("completedData", true)

            setResult(RESULT_OK, i)

            finish()
        }
    }
}