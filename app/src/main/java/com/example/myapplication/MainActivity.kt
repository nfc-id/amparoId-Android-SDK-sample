package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import cl.amparo.id.api.AmparoIdApi
import cl.amparo.id.api.AmparoIdApiFactory
import cl.amparo.id.api.AmparoIdResultContract
import cl.amparo.id.api.AmparoIdSdkConfig
import cl.amparo.id.api.AmparoIdSdkResponse
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private var amparoIdSdk : AmparoIdApi? = null

    private val amparoLauncher = registerForActivityResult(AmparoIdResultContract()) { result ->
        when (result) {
            AmparoIdSdkResponse.SUCCESS -> onSuccess()
            AmparoIdSdkResponse.CANCELLED  -> onCancelled()
            else -> onFailure()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(R.layout.activity_main)
        val btnStartSdk = findViewById<Button>(R.id.btnStartSdk)
        btnStartSdk.setOnClickListener {
            callAmparoIdSdkActivity()
        }
    }

    private fun callAmparoIdSdkActivity() {
        val phoneNumber = "56999999999"
        val message = "Hello. I need help with the identification flow"

        val amparoIdSdkConfig = AmparoIdSdkConfig.Builder()
            .setApiKey("API_KEY")
            .setECert("E_CERT")
            .setKsmKey("KSM_KEY")
            .setSkmKey("SKM_KEY")
            .setBaseUrl("BASE_URL") // without protocol
            .setContactSupport { openWhatsApp(phoneNumber, message) }
            .build()

        amparoIdSdk = AmparoIdApiFactory.create()
        amparoIdSdk!!.startActivityForResult(this@MainActivity, amparoIdSdkConfig, amparoLauncher)
    }

    private fun openWhatsApp(phoneNumber: String, message: String) {
        try {
            val url = "https://wa.me/$phoneNumber?text=${URLEncoder.encode(message, "UTF-8")}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.setPackage("com.whatsapp")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error opening WhatsApp.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSuccess() {
        Toast.makeText(this, "SDK Result Success", Toast.LENGTH_LONG).show()
    }

    private fun onCancelled() {
        Toast.makeText(this, "SDK Cancelled", Toast.LENGTH_LONG).show()
    }

    private fun onFailure() {
        Toast.makeText(this, "SDK Failed", Toast.LENGTH_LONG).show()
    }
}

