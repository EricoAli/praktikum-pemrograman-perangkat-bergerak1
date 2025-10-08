package com.example.disasterapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disasterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Memanggil fungsi untuk membuat data dummy dan menginisialisasi adapter [cite: 170, 241]
        val adapterDisaster = DisasterAdapter(generateDummy()) { disaster ->
            // Aksi yang dilakukan saat item di-klik, yaitu menampilkan Toast [cite: 241]
            Toast.makeText(
                this@MainActivity,
                "You clicked on ${disaster.nameDisaster}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Mengatur RecyclerView
        binding.rvDisaster.apply {
            adapter = adapterDisaster
            // Menggunakan GridLayoutManager untuk tampilan grid 2 kolom [cite: 276, 281]
            layoutManager = GridLayoutManager(this@MainActivity, 2)

            // Jika ingin tampilan daftar linear vertikal, gunakan kode di bawah ini:
            // layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    // Fungsi untuk menghasilkan daftar data bencana [cite: 136, 137]
    private fun generateDummy(): List<Disaster> {
        return listOf(
            Disaster(nameDisaster = "Tsunami", disasterType = "Natural"),
            Disaster(nameDisaster = "Volcanic Eruption", disasterType = "Natural"),
            Disaster(nameDisaster = "Earthquake", disasterType = "Natural"),
            Disaster(nameDisaster = "Flood", disasterType = "Natural"),
            Disaster(nameDisaster = "Fire", disasterType = "Natural"),
            Disaster(nameDisaster = "Nuclear Accident", disasterType = "Man-made"),
            Disaster(nameDisaster = "Terrorist Attack", disasterType = "Man-made"),
            Disaster(nameDisaster = "War", disasterType = "Man-made")
        )
    }
}