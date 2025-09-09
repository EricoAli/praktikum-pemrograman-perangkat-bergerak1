package com.example.myaccess

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat
import com.example.myaccess.databinding.ActivityHomepageBinding

class HomepageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Terima data yang dikirim dari LoginActivity
        val name = intent.getStringExtra(EXTRA_NAME) ?: "User"
        val email = intent.getStringExtra(EXTRA_EMAIL) ?: "email"
        val phone = intent.getStringExtra(EXTRA_PHONE) ?: "phone"

        // --- Logika SpannableString ---

        // 1. Membuat teks "Welcome Username"
        val welcomeText = getString(R.string.welcome_message, name)
        val welcomeSpannable = SpannableString(welcomeText)
        applyColorAndBoldSpan(welcomeSpannable, name)
        binding.txtWelcomeMessage.text = welcomeSpannable

        // 2. Membuat teks "Your email..."
        val emailText = getString(R.string.email_status_message, email)
        val emailSpannable = SpannableString(emailText)
        applyColorAndBoldSpan(emailSpannable, email)
        binding.txtEmailStatus.text = emailSpannable

        // 3. Membuat teks "Your phone..."
        val phoneText = getString(R.string.phone_status_message, phone)
        val phoneSpannable = SpannableString(phoneText)
        applyColorAndBoldSpan(phoneSpannable, phone)
        binding.txtPhoneStatus.text = phoneSpannable
    }

    /**
     * Fungsi bantuan untuk menerapkan warna dan gaya tebal pada bagian teks.
     */
    private fun applyColorAndBoldSpan(spannable: SpannableString, target: String) {
        val startIndex = spannable.toString().indexOf(target)
        if (startIndex == -1) return // Jika target tidak ditemukan, jangan lakukan apa-apa

        val endIndex = startIndex + target.length

        // Terapkan warna
        val color = ContextCompat.getColor(this, R.color.custom_blue)
        spannable.setSpan(
            ForegroundColorSpan(color),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Terapkan gaya tebal (bold)
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PHONE = "extra_phone"
    }
}