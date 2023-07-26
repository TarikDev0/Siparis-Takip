package com.tarik.zerbetsiparistakip

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityUnuttunmuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Unuttunmu : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityUnuttunmuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnuttunmuBinding.inflate(layoutInflater)
        val view = (binding.root)
        setContentView(view)

        auth = Firebase.auth

        binding.untBtnGndr.setOnClickListener {
            val email = binding.untMail.text.toString()
            if (chechEmail()){
                auth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if (it.isSuccessful)
                        Toast.makeText(this,"Gönderildi", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

        }


    }

    private fun chechEmail(): Boolean{
        val email = binding.untMail.text.toString()
        if (binding.untMail.text.toString() == "") {
            binding.untMail.error = "Lütfen Mail Adresini Giriniz."
            return false

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.untMail.error = "Lütfen Mail Adresiniz Düzgün Girdiğinizden Emin Olun."
            return false
        }
        return true
    }
}

