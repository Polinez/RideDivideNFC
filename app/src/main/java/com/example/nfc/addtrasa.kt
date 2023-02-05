package com.example.nfc

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import java.math.BigDecimal
import java.math.RoundingMode

class addtrasa : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addtrasa)
        auth = Firebase.auth
//powrot w menu
val actionbar=supportActionBar
        actionbar!!.title="powrot"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val skan = findViewById(R.id.skan)as Button
        skan.setOnClickListener{

            val intent = Intent(this, skanActivity::class.java)
            startActivity(intent)
        }


    }
    fun klik(view: View) {

        val numer = findViewById<EditText>(R.id.number1).text.toString()
        val numer3 = findViewById<EditText>(R.id.number2).text.toString()
        val checkbox = findViewById<CheckBox>(R.id.checkbox1)
        val klik = findViewById<Button>(R.id.licz)
        var cos = 0
        var cos2: Double
        var numervar:Double
        var numer3var:Double

        if(numer.trim().length>0 && numer3.trim().length>0)
        {
            val wynik = findViewById<TextView>(R.id.textView2).apply {
                if (checkbox.isChecked) {
                    cos = 1
                } else {
                    cos = 0
                }

                cos2 = numer.toDouble() / (numer3.toDouble() + cos)
                text = "Wynik: " + BigDecimal(cos2).setScale(2, RoundingMode.HALF_EVEN).toString()
            }

        }
        else if(numer.trim().length<=0 && numer3.trim().length>0)
        {
            val wynik = findViewById<TextView>(R.id.textView2).apply {

                text = "Wpisz pierwszą liczbe"
            }

        }

        else if(numer.trim().length>0 && numer3.trim().length<=0)
        {
            val wynik = findViewById<TextView>(R.id.textView2).apply {

                text = "Wpisz drugą liczbe"
            }

        }
        else if(numer.trim().length<=0 && numer3.trim().length<=0)
        {
            val wynik = findViewById<TextView>(R.id.textView2).apply {

                text = "Wpisz obie liczby"
            }

        }
    }

    //powrot
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

