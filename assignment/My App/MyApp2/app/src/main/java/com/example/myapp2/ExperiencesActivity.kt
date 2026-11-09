package com.example.myapp2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button

class ExperiencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_experiences)

        val btnVR = findViewById<Button>(R.id.btnVR)
        val btnRacing = findViewById<Button>(R.id.btnRacing)
        val btnEscape = findViewById<Button>(R.id.btnEscape)

        btnVR.setOnClickListener {
            startActivity(Intent(this, VRActivity::class.java))
        }

        btnRacing.setOnClickListener {
            startActivity(Intent(this, RacingActivity::class.java))
        }

        btnEscape.setOnClickListener {
            startActivity(Intent(this, EscapeRoomActivity::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
