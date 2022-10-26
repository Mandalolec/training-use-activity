package com.example.laba_4.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.laba_4.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var registrationDataLauncher : ActivityResultLauncher<Intent>? = null
    private var setRouteDataLauncher : ActivityResultLauncher<Intent>? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        registrationDataLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result : ActivityResult ->
            if (result.resultCode == RESULT_OK){
                val phoneNumber = result.data?.getStringExtra("phoneNumber")
                val nameUser = result.data?.getStringExtra("nameUser")

                val enabledButtonSetPath = result.data?.getBooleanExtra("enabledButtonSetPath", false)

                binding.phoneTextView.text = phoneNumber
                binding.userTextView.text = nameUser

                binding.buttonSetPath.isEnabled = enabledButtonSetPath!!
            }
        }

        setRouteDataLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result : ActivityResult ->
            if(result.resultCode == RESULT_OK){
                val startStreet = result.data?.getStringExtra("startStreet")
                val startHouse = result.data?.getStringExtra("startHouse")
                val startFlat = result.data?.getStringExtra("startFlat")

                val endStreet = result.data?.getStringExtra("endStreet")
                val endHouse = result.data?.getStringExtra("endHouse")
                val endFlat = result.data?.getStringExtra("endFlat")

                val completedData = result.data?.getBooleanExtra("completedData", false)

                binding.pathTextView.text = "Такси прибудет по адересу $startStreet, $startHouse, $startFlat через 5 минут и отвезет вас к $endStreet, $endHouse, $endFlat. Если вы согласны, то нажмите на кнопку ВЫЗВАТЬ ТАКСИ"
                binding.buttonCallTaxi.isEnabled = completedData!!
            }
        }

        registrationDataLauncher?.launch(Intent(this, RegistrationActivity::class.java))
    }

    fun onClickToSetRout(view: View){
        setRouteDataLauncher?.launch(Intent(this, SetRouteActivity::class.java))
    }

    fun onClickRegister(view : View){
        registrationDataLauncher?.launch(Intent(this, RegistrationActivity::class.java))
    }

    fun onClickCallTaxi(view: View){
        Toast.makeText(applicationContext, "Такси в пути. Удачи!", Toast.LENGTH_SHORT).show()
    }
}