package com.example.hellocompose


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellocompose.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Memanggil fungsi UI utama dengan padding
                    HelloToastScreenRowButtons()
                }
            }
        }
    }
}

// ----------------------------------------------------
// FUNGSI COMPOSABLE UTAMA (INI GABUNGANNYA)
// ----------------------------------------------------
@Composable
fun HelloToastScreen() {
    // 1. Tambahkan state dan context [cite: 118, 119]
    val ctx = LocalContext.current
    var count by rememberSaveable { mutableStateOf(0) }

    // 2. Buat layout Column [cite: 81-86]
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 3. Tombol TOAST (dengan logic onClick yang benar) [cite: 123-128]
        Button(
            onClick = {
                Toast.makeText(ctx, "Count $count", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.toast))
        }

        // 4. Box Angka (menggunakan state 'count') [cite: 93-108]
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Memberi bobot agar mengisi ruang
                .background(color = colorResource(id = R.color.yellow)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = count.toString(), // Tampilkan state 'count'
                fontSize = 64.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        // 5. Tombol COUNT (dengan logic onClick yang benar) [cite: 130-135]
        Button(
            onClick = {
                count++ // Menambah nilai state
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.count))
        }
    }
}

@Composable
fun HelloToastScreenRowButtons() {
    val ctx = LocalContext.current
    var count by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 16.dp)
                .background(color = colorResource(id =
                    R.color.yellow)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = count.toString(),
                fontSize = 64.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { Toast.makeText(ctx, "Count $count",
                    Toast.LENGTH_SHORT).show() },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(id = R.string.toast))
            }
            Button(
                onClick = { count++ },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(id = R.string.count))
            }
        }
    }
}

// ----------------------------------------------------
// FUNGSI PREVIEW [cite: 138-142]
// ----------------------------------------------------
@Preview(showBackground = true)
@Composable
fun HelloToastPreview() {
    HelloComposeTheme {
        HelloToastScreenRowButtons()
    }
}