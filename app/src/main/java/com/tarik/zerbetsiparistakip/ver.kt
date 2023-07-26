package com.tarik.zerbetsiparistakip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityVerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class ver : AppCompatActivity() {
    private lateinit var binding: ActivityVerBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        //SADE BÖREK

        binding.sadeEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var sadeborekfiyati = binding.sadeFiyat.text.toString().toDoubleOrNull() ?: 0.0
                var sadeborekadedi = s.toString().toDoubleOrNull() ?: 0.0
                var sadeborektutari = sadeborekfiyati * sadeborekadedi
                binding.sadeTutar.text = sadeborektutari.toString()
                hesaplaToplamTutar()
            }



            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnSadeEkle.setOnClickListener {
            var sadeborekadedi = binding.sadeEditText.text.toString().toIntOrNull() ?: 0
            sadeborekadedi += 1
            binding.sadeEditText.setText(sadeborekadedi.toString())
            hesaplaToplamTutar()
        }

        binding.btnSadeCikar.setOnClickListener {
            var sadeborekadedi = binding.sadeEditText.text.toString().toIntOrNull() ?: 0
            sadeborekadedi -= 1
            if (sadeborekadedi < 0) {
                sadeborekadedi = 0
            }
            binding.sadeEditText.setText(sadeborekadedi.toString())
            hesaplaToplamTutar()
        }



        //KOL BÖREĞİ


        binding.kolEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var kolborekfiyati = binding.kolFiyat.text.toString().toDoubleOrNull() ?: 0.0
                var kolborekadedi = s.toString().toDoubleOrNull() ?: 0.0
                var kolborektutari = kolborekfiyati * kolborekadedi
                binding.kolTutar.text = kolborektutari.toString()
                hesaplaToplamTutar()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnKolEkle.setOnClickListener {
            var kolborekadedi = binding.kolEditText.text.toString().toIntOrNull() ?: 0
            kolborekadedi += 1
            binding.kolEditText.setText(kolborekadedi.toString())
            hesaplaToplamTutar()
        }

        binding.btnKolCikar.setOnClickListener {
            var kolborekadedi = binding.kolEditText.text.toString().toIntOrNull() ?: 0
            kolborekadedi -= 1
            if (kolborekadedi < 0) {
                kolborekadedi = 0
            }
            binding.kolEditText.setText(kolborekadedi.toString())
            hesaplaToplamTutar()
        }


        //POĞAÇA


        binding.pogacaEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var pogacafiyati = binding.pogacaFiyat.text.toString().toDoubleOrNull() ?: 0.0
                var pogacaadedi = s.toString().toDoubleOrNull() ?: 0.0
                var pogacatutari = pogacafiyati * pogacaadedi
                binding.pogacaTutar.text = pogacatutari.toString()
                hesaplaToplamTutar()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnPogacaEkle.setOnClickListener {
            var pogacaadedi = binding.pogacaEditText.text.toString().toIntOrNull() ?: 0
            pogacaadedi += 1
            binding.pogacaEditText.setText(pogacaadedi.toString())
            hesaplaToplamTutar()
        }

        binding.btnPogacaCikar.setOnClickListener {
            var pogacaadedi = binding.pogacaEditText.text.toString().toIntOrNull() ?: 0
            pogacaadedi -= 1
            if (pogacaadedi < 0) {
                pogacaadedi = 0
            }
            binding.pogacaEditText.setText(pogacaadedi.toString())
            hesaplaToplamTutar()
        }


        // AÇMA

        binding.acmaEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var acmafiyati = binding.acmaFiyat.text.toString().toDoubleOrNull() ?: 0.0
                var acmaadedi = s.toString().toDoubleOrNull() ?: 0.0
                var acmatutari = acmafiyati * acmaadedi
                binding.acmaTutar.text = acmatutari.toString()
                hesaplaToplamTutar()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnAcmaEkle.setOnClickListener {
            var acmaadedi = binding.acmaEditText.text.toString().toIntOrNull() ?: 0
            acmaadedi += 1
            binding.acmaEditText.setText(acmaadedi.toString())
            hesaplaToplamTutar()
        }

        binding.btnAcmaCikar.setOnClickListener {
            var acmaadedi = binding.acmaEditText.text.toString().toIntOrNull() ?: 0
            acmaadedi -= 1
            if (acmaadedi < 0) {
                acmaadedi = 0
            }
            binding.acmaEditText.setText(acmaadedi.toString())
            hesaplaToplamTutar()
        }


        //SİMİT

        binding.simitEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var simitfiyati = binding.simitFiyat.text.toString().toDoubleOrNull() ?: 0.0
                var simitadedi = s.toString().toDoubleOrNull() ?: 0.0
                var simittutari = simitfiyati * simitadedi
                binding.simitTutar.text = simittutari.toString()
                hesaplaToplamTutar()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnSimitEkle.setOnClickListener {
            var simitadedi = binding.simitEditText.text.toString().toIntOrNull() ?: 0
            simitadedi += 1
            binding.simitEditText.setText(simitadedi.toString())
            hesaplaToplamTutar()
        }

        binding.btnSimitCikar.setOnClickListener {
            var simitadedi = binding.simitEditText.text.toString().toIntOrNull() ?: 0
            simitadedi -= 1
            if (simitadedi < 0) {
                simitadedi = 0
            }
            binding.simitEditText.setText(simitadedi.toString())
            hesaplaToplamTutar()
        }


        //OTLU

        binding.otluEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var otlufiyati = binding.otluFiyat.text.toString().toDoubleOrNull() ?: 0.0
                var otluadedi = s.toString().toDoubleOrNull() ?: 0.0
                var otlututari = otlufiyati * otluadedi
                binding.otluTutar.text = otlututari.toString()
                hesaplaToplamTutar()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnOtluEkle.setOnClickListener {
            var otluadedi = binding.otluEditText.text.toString().toIntOrNull() ?: 0
            otluadedi += 1
            binding.otluEditText.setText(otluadedi.toString())
            hesaplaToplamTutar()
        }

        binding.btnOtluCikar.setOnClickListener {
            var otluadedi = binding.otluEditText.text.toString().toIntOrNull() ?: 0
            otluadedi -= 1
            if (otluadedi < 0) {
                otluadedi = 0
            }
            binding.otluEditText.setText(otluadedi.toString())
            hesaplaToplamTutar()
        }


        //İÇLİ

        binding.icliEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var iclifiyati = binding.icliFiyat.text.toString().toDoubleOrNull() ?: 0.0
                var icliadedi = s.toString().toDoubleOrNull() ?: 0.0
                var iclitutari = iclifiyati * icliadedi
                binding.icliTutar.text = iclitutari.toString()
                hesaplaToplamTutar()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnIcliEkle.setOnClickListener {
            var icliadedi = binding.icliEditText.text.toString().toIntOrNull() ?: 0
            icliadedi += 1
            binding.icliEditText.setText(icliadedi.toString())
            hesaplaToplamTutar()
        }

        binding.btnIcliCikar.setOnClickListener {
            var icliadedi = binding.icliEditText.text.toString().toIntOrNull() ?: 0
            icliadedi -= 1
            if (icliadedi < 0) {
                icliadedi = 0
            }
            binding.icliEditText.setText(icliadedi.toString())
            hesaplaToplamTutar()
            }


        binding.btnVer.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val currentUser = FirebaseAuth.getInstance().currentUser
                val gunluk = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

                // Kullanıcının şube adını Firebase veritabanından alın
                val database = Firebase.database.reference.child("Şubeler")
                database.child(currentUser!!.uid).addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            val subeAdi = snapshot.child("Şube Adı").value.toString()
                            val isimsoyisim = snapshot.child("Ad Soyad").value.toString()


                            // "Siparişler" child'inden hemen sonra şube adı child'ini oluşturun
                            var database = Firebase.database.reference.child("Siparişler").child("$isimsoyisim $subeAdi").child(gunluk)

                            var sadeBorek = binding.sadeEditText.text.toString()
                            var kolBorek =binding.kolEditText.text.toString()
                            var pogaca =binding.pogacaEditText.text.toString()
                            var acma =binding.acmaEditText.text.toString()
                            var simit =binding.simitEditText.text.toString()
                            var otlu =binding.otluEditText.text.toString()
                            var icli =binding.icliEditText.text.toString()
                            var toplamTutar=binding.toplamTutar.text.toString()

                            var veriler = hashMapOf(
                                "1 Sade Börek" to sadeBorek,
                                "2 Kol Böreği" to kolBorek,
                                "3 Poğaca" to pogaca,
                                "4 Açma" to acma,
                                "5 Simit" to simit,
                                "6 Otlu" to otlu,
                                "7 İçli" to icli,
                                "8 Toplam Tutar" to toplamTutar
                            )

                            database.setValue(veriler)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("TAG", "Error fetching subeAdi: ${error.message}")
                    }
                })
            }
        })




    }/// onCreate

    //TOPLAM TUTAR FONKSİYONU
    private fun hesaplaToplamTutar() {
        val sadeTutar = binding.sadeTutar.text.toString().toDoubleOrNull() ?: 0.0
        val kolTutar = binding.kolTutar.text.toString().toDoubleOrNull() ?: 0.0
        val pogacaTutar = binding.pogacaTutar.text.toString().toDoubleOrNull() ?: 0.0
        val acmaTutar = binding.acmaTutar.text.toString().toDoubleOrNull() ?: 0.0
        val simitTutar = binding.simitTutar.text.toString().toDoubleOrNull() ?: 0.0
        val otluTutar = binding.otluTutar.text.toString().toDoubleOrNull() ?: 0.0
        val icliTutar = binding.icliTutar.text.toString().toDoubleOrNull() ?: 0.0
        val toplamTutar = sadeTutar + kolTutar + pogacaTutar + acmaTutar + simitTutar + otluTutar + icliTutar
        binding.toplamTutar.text =   toplamTutar.toString() + "₺"














    }
}

