package com.tarik.zerbetsiparistakip


import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityGirisBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Giris : AppCompatActivity() {
    private lateinit var binding: ActivityGirisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGirisBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button6.setOnClickListener {
            val user = FirebaseAuth.getInstance().currentUser
            val userRef = FirebaseDatabase.getInstance().getReference("Şubeler").child(user?.uid ?: "")
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val isAdmin = snapshot.child("isAdmin").getValue(Boolean::class.java) ?: false
                    if (isAdmin) {
                        // admin ise diğer sayfaya yönlendirin
                        val intent = Intent(this@Giris, siparislerAdmin::class.java)
                        startActivity(intent)
                    } else {
                        // normal kullanıcı ise diğer sayfaya yönlendirin
                        val intent = Intent(this@Giris, SiparislerUser::class.java)
                        startActivity(intent)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, "loadUser:onCancelled", error.toException())
                }
            })
        }




        binding.btnsiparis.setOnClickListener {
            startActivity(Intent(this@Giris, ver::class.java))
        }

        binding.btnGrsHesabim.setOnClickListener {
            startActivity(Intent(this@Giris, Hesabim::class.java))
        }














        }


    }











