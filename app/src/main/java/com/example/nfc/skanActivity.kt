package com.example.nfc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class skanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skan_activity)
        //powrot guzik
        val actionbar = supportActionBar
        actionbar!!.title = "powrot"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
        //powrot
        override fun onSupportNavigateUp(): Boolean {
            onBackPressed()
            return true
    }
}