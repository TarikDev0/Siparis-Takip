package com.tarik.zerbetsiparistakip

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivitySiparislerAdminBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class siparislerAdmin : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<SiparislerR>
    private lateinit var binding: ActivitySiparislerAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySiparislerAdminBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        newRecyclerView = binding.recyclerView
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<SiparislerR>()
        getUserdata()



    }






    private fun getUserdata() {
        FirebaseDatabase.getInstance().getReference("Siparişler")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val siparisler = snapshot.children.mapNotNull { subeSnapshot ->
                        val subeAdi = subeSnapshot.key.toString()
                        val siparis = SiparislerR(subeAdi)
                        subeSnapshot.children.forEach { tarihSnapshot ->
                            tarihSnapshot.children.forEach { siparisSnapshot ->
                                val siparisTipi = siparisSnapshot.key.toString()
                                val siparisAdeti =
                                    siparisSnapshot.getValue(String::class.java)?.toIntOrNull() ?: 0
                                when (siparisTipi) {
                                    "1 Sade Börek" -> siparis.sadeAdet += siparisAdeti
                                    "2 Kol Böreği" -> siparis.kolAdet += siparisAdeti
                                    "3 Poğaca" -> siparis.pogacaAdet += siparisAdeti
                                    "4 Açma" -> siparis.acmaAdet += siparisAdeti
                                    "5 Simit" -> siparis.simitAdet += siparisAdeti
                                    "6 Otlu" -> siparis.otluAdet += siparisAdeti
                                    "7 İçli" -> siparis.icliAdet += siparisAdeti
                                    "8 Toplam Tutar" -> siparis.toplamTutar =
                                        siparisSnapshot.getValue(String::class.java) ?: ""
                                }
                            }
                        }
                        newArrayList.add(siparis)
                    }

                    val adapter = MyAdapter(newArrayList)
                    newRecyclerView.adapter = adapter
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(
                        "SiparislerAdmin",
                        "Failed to read Siparisler value.",
                        error.toException()
                    )
                }
            })
    }
}