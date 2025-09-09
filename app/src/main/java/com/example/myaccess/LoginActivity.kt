package com.example.myaccess

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myaccess.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // Variabel untuk menyimpan data registrasi sementara
    private var registeredUsername: String? = null
    private var registeredEmail: String? = null
    private var registeredPhone: String? = null
    private var registeredPassword: String? = null

    private val registerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data

            registeredUsername = data?.getStringExtra(RegisterActivity.EXTRA_USERNAME)
            registeredEmail = data?.getStringExtra(RegisterActivity.EXTRA_EMAIL)
            registeredPhone = data?.getStringExtra(RegisterActivity.EXTRA_PHONE)
            registeredPassword = data?.getStringExtra(RegisterActivity.EXTRA_PASSWORD)

            binding.edtUsername.setText(registeredUsername)
            binding.edtPassword.text.clear()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val inputUsername = binding.edtUsername.text.toString()
            val inputPassword = binding.edtPassword.text.toString()

            if (inputUsername == registeredUsername && inputPassword == registeredPassword && registeredUsername != null) {
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                val intentToHomepage = Intent(this@LoginActivity, HomepageActivity::class.java).apply {
                    putExtra(HomepageActivity.EXTRA_NAME, registeredUsername)
                    putExtra(HomepageActivity.EXTRA_EMAIL, registeredEmail)
                    putExtra(HomepageActivity.EXTRA_PHONE, registeredPhone)
                }
                startActivity(intentToHomepage)

            } else if (registeredUsername == null) {
                Toast.makeText(this, "Silakan register terlebih dahulu!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Username atau Password Salah!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtToRegister.setOnClickListener {
            val intentToRegister = Intent(this@LoginActivity, RegisterActivity::class.java)
            registerLauncher.launch(intentToRegister)
        }
    }
}