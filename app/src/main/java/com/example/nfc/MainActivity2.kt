package com.example.nfc
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity



class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val loginActivitybutton=findViewById<Button>(R.id.login)
        loginActivitybutton.setOnClickListener{
            val Intent= Intent(this, com.example.nfc.loginActivity::class.java)
            startActivity(Intent)
        }

        val Registerbutton=findViewById<Button>(R.id.register)
       Registerbutton.setOnClickListener{
            val Intent= Intent(this, com.example.nfc.Register::class.java)
            startActivity(Intent)
        }
    }
}

