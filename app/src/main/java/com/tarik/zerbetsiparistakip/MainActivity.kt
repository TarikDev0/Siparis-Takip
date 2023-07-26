package com.tarik.zerbetsiparistakip
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth

        binding.grsKydlbtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, Kaydol::class.java))
        }

        binding.grsBtnGiris.setOnClickListener {
            val email = binding.grsMail.text.toString()
            val parola = binding.grsParola.text.toString()
            if(chechAllField()){
                auth.signInWithEmailAndPassword(email, parola).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this, "Giriş Başarılı", Toast.LENGTH_SHORT ).show()
                        val intent = Intent(this, Giris::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Log.e("error: ", it.exception.toString())
                    }
                }
            }
        }

        binding.grsUnutunmu.setOnClickListener{
            val intent = Intent(this, Unuttunmu::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun chechAllField(): Boolean {
        val email = binding.grsMail.text.toString()
        if (binding.grsMail.text.toString() == "") {
            binding.grsMail.error = "Lütfen Mail Adresini Giriniz."
            return false

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.grsMail.error = "Lütfen Mail Adresiniz Düzgün Girdiğinizden Emin Olun."
            return false
        }
        if (binding.grsParola.text.toString() == "") {
            binding.grsParola.error = "Lütfen Parola Giriniz."
            return false
        }
        if(binding.grsParola.length() <=4) {
            binding.grsParola.error = "Parola en az 6 karakterden oluşmalı."
            return false
        }
        return true
    }
}