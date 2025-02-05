package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import cl.bice.id.amparoApi.AmparoIdApi
import cl.bice.id.amparoApi.AmparoIdProvider
import cl.bice.id.amparoApi.AmparoIdSdkConfig

class MainActivity : AppCompatActivity() {
    private var amparoIdSdk : AmparoIdApi? = null

    private val amparoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val sdkResult = result.data?.getStringExtra("SDK_RESULT")
            Toast.makeText(this, "SDK Result: $sdkResult", Toast.LENGTH_LONG).show()
            // Handle success
        } else {
            Toast.makeText(this, "SDK Cancelled or Failed", Toast.LENGTH_LONG).show()
            // Handle error or cancellation
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val amparoIdSdkConfig = AmparoIdSdkConfig.Builder()
            .setApiKey("API_KEY")
            .setContactCallbackUrl("https://wa.me/56912345678")
            .build()

        amparoIdSdk = AmparoIdProvider.create()
        amparoIdSdk!!.startActivityForResult(this@MainActivity, amparoIdSdkConfig, amparoLauncher)
    }
}

