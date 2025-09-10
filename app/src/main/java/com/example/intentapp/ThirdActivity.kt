package com.example.intentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intentapp.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        with(binding) {
            btnToSecondActivity.setOnClickListener {
                val resultIntent = Intent()
                resultIntent.putExtra(SecondActivity.EXTRA_ADDRESS, edtAddress.text.toString())
                resultIntent.putExtra(MainActivity.EXTRA_NAME, name)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}