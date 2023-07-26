package com.tarik.zerbetsiparistakip

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityHesabimBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Hesabim : AppCompatActivity() {
    private lateinit var binding: ActivityHesabimBinding
    private lateinit var auth: FirebaseAuth
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHesabimBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        binding.hsbBtnParola.setOnClickListener {
            val user = auth.currentUser
            val parola = binding.hsbParolaEdit.text.toString()
            if(kontrolParolaAlani()) {
                user?.updatePassword(parola)?.addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Parola Değiştirme İşlemi Başarılı.", Toast.LENGTH_SHORT).show()
                   val intent = Intent(this, Giris::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.e("error: ", it.exception.toString())
                    }

                }

            }
        }

        binding.hsbBtnMail.setOnClickListener {
            val user = auth.currentUser
            val email = binding.hsbMailEdit.text.toString()
            if (kontrolMailAlani()){
                user?.updateEmail(email)?.addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this,"EMail Değiştirme İşlemi Başarılı.", Toast.LENGTH_SHORT).show()
                        val intent =Intent(this, Giris::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.e("error: ", it.exception.toString())
                    }
                }
            }
        }

    }

    private fun kontrolParolaAlani(): Boolean {
        if (binding.hsbParolaEdit.text.toString() == "") {
            binding.hsbParolaEdit.error = "Lütfen Parola Giriniz."
            return false
        }
        if(binding.hsbParolaEdit.length() <=4) {
            binding.hsbParolaEdit.error = "Parola en az 6 karakterden oluşmalı."
            return false
        }
        if (binding.hsbParolaTEdit.text.toString() == "") {
            binding.hsbParolaTEdit.error = "Lütfen Parola Giriniz."
            return false
        }
        if(binding.hsbParolaEdit.text.toString() != binding.hsbParolaTEdit.text.toString()) {
            binding.hsbParolaTEdit.error = "Parolalar Eşleşmiyor"
            return false
        }
        return true
    }

    private fun kontrolMailAlani(): Boolean {
        val email = binding.hsbMailEdit.text.toString()

        if (binding.hsbMailEdit.text.toString() == "") {
            binding.hsbMailEdit.error = "Lütfen Mail Adresini Giriniz."
            return false

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.hsbMailEdit.error = "Lütfen Mail Adresiniz Düzgün Girdiğinizden Emin Olun."
            return false
        }
        return true
    }
}