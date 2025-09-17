package com.example.presensi

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.presensi.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Variabel untuk menyimpan data pilihan user
    private var selectedDate: String = ""
    private var selectedTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- Inisialisasi dan Setup Awal ---
        setupInitialValues()
        setupDropdown()
        setupListeners()
        setupExitDialog()
    }

    private fun setupInitialValues() {
        // Set tanggal hari ini sebagai default
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        selectedDate = dateFormat.format(calendar.time)
    }

    private fun setupDropdown() {
        val statusItems = resources.getStringArray(R.array.status_presensi)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, statusItems)
        binding.autoCompleteTextView.setAdapter(adapter)
    }

    private fun setupListeners() {
        // 1. Listener untuk Kalender
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
            selectedDate = dateFormat.format(calendar.time)
        }

        // 2. Listener untuk Tombol Pilih Waktu
        binding.btnTimePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)
                    binding.btnTimePicker.text = selectedTime
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true // Gunakan format 24 jam
            )
            timePickerDialog.show()
        }

        // 3. Listener untuk Dropdown Status
        binding.autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val selectedStatus = binding.autoCompleteTextView.text.toString()
            // Tampilkan field keterangan jika status "Sakit" atau "Izin"
            if (selectedStatus == "Sakit" || selectedStatus == "Izin") {
                binding.layoutKeterangan.visibility = View.VISIBLE
            } else {
                binding.layoutKeterangan.visibility = View.GONE
            }
        }

        // 4. Listener untuk Tombol Submit
        binding.btnSubmit.setOnClickListener {
            handleSubmit()
        }
    }

    private fun handleSubmit() {
        val status = binding.autoCompleteTextView.text.toString()
        val keterangan = binding.etKeterangan.text.toString()

        // Validasi sederhana
        if (status.isEmpty()) {
            Toast.makeText(this, "Silakan pilih status kehadiran!", Toast.LENGTH_SHORT).show()
            return
        }
        if (selectedTime.isEmpty()) {
            Toast.makeText(this, "Silakan pilih waktu!", Toast.LENGTH_SHORT).show()
            return
        }
        if ((status == "Sakit" || status == "Izin") && keterangan.isEmpty()) {
            binding.layoutKeterangan.error = "Keterangan tidak boleh kosong"
            return
        } else {
            binding.layoutKeterangan.error = null
        }

        // Tampilkan pesan sukses
        val successMessage = "Presensi berhasil: $status pada $selectedDate jam $selectedTime"
        Toast.makeText(this, successMessage, Toast.LENGTH_LONG).show()

        // Di sini Anda bisa menambahkan logika lain, seperti mengirim data ke server
    }

    private fun setupExitDialog() {
        // Ini adalah cara modern menangani tombol back
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitConfirmationDialog()
            }
        })
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Keluar")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
            .setPositiveButton("Ya") { _, _ ->
                // Jika user menekan "Ya", keluar dari aplikasi
                finish()
            }
            .setNegativeButton("Tidak", null) // "Tidak" tidak melakukan apa-apa
            .show()
    }
}