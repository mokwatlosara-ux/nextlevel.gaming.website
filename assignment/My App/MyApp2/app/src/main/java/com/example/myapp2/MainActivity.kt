package com.example.myapp2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnAbout = findViewById<Button>(R.id.btnAbout)
        val btnPackages = findViewById<Button>(R.id.btnPackages)
        val btnExperiences = findViewById<Button>(R.id.btnExperiences)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val btnContact = findViewById<Button>(R.id.btnContact)

        btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        btnPackages.setOnClickListener {
            startActivity(Intent(this, PackagesActivity::class.java))
        }

        btnExperiences.setOnClickListener {
            startActivity(Intent(this, ExperiencesActivity::class.java))
        }

        btnCalculate.setOnClickListener {
            startActivity(Intent(this, CalculateFeesActivity::class.java))
        }

        btnContact.setOnClickListener {
            startActivity(Intent(this, ContactActivity::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
