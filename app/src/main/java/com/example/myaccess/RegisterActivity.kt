package com.example.myaccess

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.util.Patterns
import com.example.myaccess.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val username = binding.edtUsernameRegister.text.toString()
            val email = binding.edtEmailRegister.text.toString()
            val phone = binding.edtPhoneRegister.text.toString()
            val password = binding.edtPasswordRegister.text.toString()

            // 1. Cek dulu apakah ada kolom yang kosong
            if (username.isBlank() || email.isBlank() || phone.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Hentikan proses jika ada yang kosong
            }

            // 2. Jika semua terisi, cek format email
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Format email tidak valid!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Hentikan proses jika email tidak valid
            }

            // 3. Jika semua validasi lolos, lanjutkan proses registrasi
            Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_USERNAME, username)
            resultIntent.putExtra(EXTRA_EMAIL, email)
            resultIntent.putExtra(EXTRA_PHONE, phone)
            resultIntent.putExtra(EXTRA_PASSWORD, password)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } // Penutup untuk setOnClickListener
    } // Penutup untuk onCreate

    // companion object harus berada di dalam class, tapi di luar method
    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PHONE = "extra_phone"
        const val EXTRA_PASSWORD = "extra_password"
    }
} // Penutup untuk class RegisterActivity