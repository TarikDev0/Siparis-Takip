package com.tarik.zerbetsiparistakip


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySiparislerUserBinding


class SiparislerUser : AppCompatActivity() {
    private lateinit var binding: ActivitySiparislerUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySiparislerUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}