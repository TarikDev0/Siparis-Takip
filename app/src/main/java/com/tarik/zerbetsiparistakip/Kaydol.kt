package com.tarik.zerbetsiparistakip
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityKaydolBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Kaydol : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityKaydolBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKaydolBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth

        binding.kydlBtnKydl.setOnClickListener {
            val email = binding.kydlMail.text.toString()
            val password = binding.kydlParola.text.toString()
            if(checkAllField()){
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val user = auth.currentUser
                        val subeAdi = binding.kydlSubeAdi.text.toString()

                        // Firebase veritabanında kullanıcı bilgilerini kaydedin
                        val database = Firebase.database
                        val usersRef = database.getReference("Şubeler")
                        if (user != null) {
                            usersRef.child(user.uid).setValue(mapOf(
                                "Ad Soyad" to binding.kydlAdSoyad.text.toString(),
                                "Şube Adı" to subeAdi,
                                "Telefon" to binding.kydlTel.text.toString(),
                                "Adres" to binding.kydlAdres.text.toString()
                            ))
                        }
                        auth.signOut()
                        Toast.makeText(this, "Hesap oluşturuldu.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        Log.e("error: ", it.exception.toString())
                    }
                }
            }

        }
    }

    private fun checkAllField(): Boolean {
        val email = binding.kydlMail.text.toString()

        if (binding.kydlAdSoyad.text.toString() ==""){
            binding.kydlAdSoyad.error = "Lütfen Adınızı Soyadınızı Giriniz"
            return false
        }
        if (binding.kydlSubeAdi.text.toString()==""){
            binding.kydlSubeAdi.error = "Lütfen Şube Adını Yazınız."
            return false
        }
        if (binding.kydlParola.text.toString() == "") {
            binding.kydlParola.error = "Lütfen Parola Giriniz."
            return false
        }
        if(binding.kydlParola.length() <=4) {
            binding.kydlParola.error = "Parola en az 6 karakterden oluşmalı."
            return false
        }
        if (binding.kydlParolaT.text.toString() == "") {
            binding.kydlParolaT.error = "Lütfen Parola Giriniz."
            return false
        }
        if(binding.kydlParola.text.toString() != binding.kydlParolaT.text.toString()){
            binding.kydlParolaT.error = "Parolalar Eşleşmiyor"
            return false
        }
        if (binding.kydlMail.text.toString() == "") {
            binding.kydlMail.error = "Lütfen Mail Adresini Giriniz."
            return false

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.kydlMail.error = "Lütfen Mail Adresiniz Düzgün Girdiğinizden Emin Olun."
            return false
        }
        if (binding.kydlTel.text.toString()==""){
            binding.kydlTel.error = "Lütfen Cep Telefon Numaranızı Giriniz."
            return false
        }

        if (binding.kydlAdres.text.toString() == "") {
            binding.kydlAdres.error = "Lütfen Şube Adresinizi Giriniz."
            return false
        }


        return true
    }

}
