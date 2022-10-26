package com.example.laba_4.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.laba_4.databinding.ActivityRegistrationBinding


class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        if(binding.phoneEditText.text.toString().trim().isEmpty() || binding.nameEditText.text.toString().trim().isEmpty() || binding.surnameEditText.text.toString().trim().isEmpty()){
            binding.buttonRegistration.text = "Зарегистрироваться"
        }else{
            binding.buttonRegistration.text = "Войти"
        }
    }

    fun onClickGoMain(view : View){
        if (binding.phoneEditText.text.toString().trim().length > 6
            && binding.nameEditText.text.toString().trim().length > 1
            && binding.surnameEditText.text.toString().trim().isNotEmpty()){
            val i = Intent(this, MainActivity::class.java)

            i.putExtra("phoneNumber", binding.phoneEditText.text.toString())
            i.putExtra("nameUser", binding.nameEditText.text.toString() + " " + binding.surnameEditText.text.toString())

            i.putExtra("enabledButtonSetPath", true)

            setResult(RESULT_OK, i)

            finish()
        }else {
            Toast.makeText(applicationContext, "Мало данных ", Toast.LENGTH_SHORT).show()
        }

        saveData()
    }

    private fun saveData(){
        val insertTextPhoneNumber: String = binding.phoneEditText.text.toString()
        val insertTextName: String = binding.nameEditText.text.toString()
        val insertTextSurname: String = binding.surnameEditText.text.toString()

        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.apply{
            putString("PHONE_KEY", insertTextPhoneNumber)
            putString("NAME_KEY",insertTextName)
            putString("SURNAME_KEY", insertTextSurname)
        }.apply()

        Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show()
    }

    private fun loadData(){
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedStringPhoneNumber: String? = sharedPreferences.getString("PHONE_KEY", null)
        val savedStringName: String? = sharedPreferences.getString("NAME_KEY", null)
        val savedStringSurname: String? = sharedPreferences.getString("SURNAME_KEY", null)

        binding.phoneEditText.setText(savedStringPhoneNumber)
        binding.nameEditText.setText(savedStringName)
        binding.surnameEditText.setText(savedStringSurname)
    }
}