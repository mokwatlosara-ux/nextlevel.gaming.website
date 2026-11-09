package com.example.myapp2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
data class Booking(val name: String, val price: Double)

class CalculateFeesActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtPhone: EditText
    private lateinit var edtEmail: EditText

    private lateinit var cbUltimate: CheckBox
    private lateinit var cbVIP: CheckBox
    private lateinit var cbEsports: CheckBox
    private lateinit var cbBirthday: CheckBox
    private lateinit var cbVR: CheckBox
    private lateinit var cbRacing: CheckBox
    private lateinit var cbEscape: CheckBox

    private lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculate_fees)
        edtName = findViewById(R.id.edtName)
        edtPhone = findViewById(R.id.edtPhone)
        edtEmail = findViewById(R.id.edtEmail)

        cbUltimate = findViewById(R.id.cbUltimate)
        cbVIP = findViewById(R.id.cbVIP)
        cbEsports = findViewById(R.id.cbEsports)
        cbBirthday = findViewById(R.id.cbBirthday)
        cbVR = findViewById(R.id.cbVR)
        cbRacing = findViewById(R.id.cbRacing)
        cbEscape = findViewById(R.id.cbEscape)

        txtResult = findViewById(R.id.txtResult)

        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        btnCalculate.setOnClickListener {
            calculateTotal()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calculateTotal() {
        edtName.error = null
        edtPhone.error = null
        edtEmail.error = null
        if (edtName.text.toString().trim().isEmpty()) {
            edtName.error = "Please enter your name"
            edtName.requestFocus()
            return
        }
        val phone = edtPhone.text.toString().trim()
        if (phone.isEmpty()) {
            edtPhone.error = "Please enter your phone number"
            edtPhone.requestFocus()
            return
        }
        if (phone.length < 10) {
            edtPhone.error = "Enter a valid phone number"
            edtPhone.requestFocus()
            return
        }
        val email = edtEmail.text.toString().trim()
        if (email.isEmpty()) {
            edtEmail.error = "Please enter your email"
            edtEmail.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.error = "Enter a valid email address"
            edtEmail.requestFocus()
            return
        }
        val selectedBookings = mutableListOf<Booking>()

        if (cbUltimate.isChecked) {
            selectedBookings.add(Booking("Ultimate Gamer Pass", 1500.0))
        }
        if (cbVIP.isChecked) {
            selectedBookings.add(Booking("VIP Gaming Experience", 1500.0))
        }
        if (cbEsports.isChecked) {
            selectedBookings.add(Booking("Esports Training Package", 1500.0))
        }
        if (cbBirthday.isChecked) {
            selectedBookings.add(Booking("Birthday Party Package", 1500.0))
        }
        if (cbVR.isChecked) {
            selectedBookings.add(Booking("Virtual Reality Experience", 750.0))
        }
        if (cbRacing.isChecked) {
            selectedBookings.add(Booking("Racing Simulator Challenge", 750.0))
        }
        if (cbEscape.isChecked) {
            selectedBookings.add(Booking("Escape Room Challenge", 750.0))
        }
        if (selectedBookings.isEmpty()) {
            Toast.makeText(this, "Please select at least one booking", Toast.LENGTH_SHORT).show()
            return
        }
        var subtotal = 0.0
        for (booking in selectedBookings) {
            subtotal += booking.price
        }
        val discountRate = when {
            selectedBookings.size == 1 -> 0.0
            selectedBookings.size == 2 -> 0.05
            selectedBookings.size == 3 -> 0.10
            else -> 0.15
        }

        val discountAmount = subtotal * discountRate
        val amountAfterDiscount = subtotal - discountAmount
        val vat = amountAfterDiscount * 0.15
        val total = amountAfterDiscount + vat
        val result = StringBuilder()
        result.append("NEXT LEVEL GAMING & ESPORTS ARENA\n\n")
        result.append("Customer: ${edtName.text}\n")
        result.append("Phone: ${edtPhone.text}\n")
        result.append("Email: ${edtEmail.text}\n\n")
        result.append("SELECTED BOOKINGS\n")

        for (booking in selectedBookings) {
            result.append("${booking.name} - R${"%.2f".format(booking.price)}\n")
        }

        result.append("\n--------------------------\n")
        result.append("Subtotal: R${"%.2f".format(subtotal)}\n")
        result.append("Discount: ${(discountRate * 100).toInt()}%\n")
        result.append("Discount Amount: R${"%.2f".format(discountAmount)}\n")
        result.append("After Discount: R${"%.2f".format(amountAfterDiscount)}\n")
        result.append("VAT (15%): R${"%.2f".format(vat)}\n")
        result.append("\nTOTAL QUOTATION: R${"%.2f".format(total)}")
        result.append("\n\nThis is a quotation only and is not a formal invoice.")

        txtResult.text = result.toString()
    }
}
