package com.example.myapp2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button

class PackagesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_packages)

        val btnUltimate = findViewById<Button>(R.id.btnUltimate)
        val btnVIP = findViewById<Button>(R.id.btnVIP)
        val btnEsports = findViewById<Button>(R.id.btnEsports)
        val btnBirthday = findViewById<Button>(R.id.btnBirthday)

        btnUltimate.setOnClickListener {
            startActivity(Intent(this, UltimateGamerActivity::class.java))
        }

        btnVIP.setOnClickListener {
            startActivity(Intent(this, VipGamingActivity::class.java))
        }

        btnEsports.setOnClickListener {
            startActivity(Intent(this, EsportsTrainingActivity::class.java))
        }

        btnBirthday.setOnClickListener {
            startActivity(Intent(this, BirthdayPartyActivity::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
