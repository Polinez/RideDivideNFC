package com.example.nfc

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nfc.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.tasks.await
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {


    private lateinit var database: DatabaseReference

    database = Firebase.database.reference

   // private val personCollectionName = Firebase.firestore.collection("uzytkownik")
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {

            val intent = Intent(this, addtrasa::class.java)
            startActivity(intent)
        }
        //do bazy danych "uzytkownik"
       // subscribeToRealtimeUpdates()
        val btnRetrieveData = findViewById<Button>(R.id.btnRetrieveData)
        btnRetrieveData.setOnClickListener {
            retrivePerson()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        auth.signOut()
        val logoutIntent = Intent(this, loginActivity::class.java)
        logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(logoutIntent)

        Toast.makeText(this, "Zostałes wylogowany", Toast.LENGTH_SHORT).show()



        return super.onOptionsItemSelected(item)
    }



private fun retrivePerson() = CoroutineScope(Dispatchers.IO).launch {
    try {
        val querySnapshot = personCollectionName.get().await()
        val sb = StringBuilder()
        for (document in querySnapshot.documents) {
            val users = document.toObject<users>()
            sb.append("$users\n")
        }
        withContext(Dispatchers.Main) {
           /* val uzytkownik: TextView = findViewById(R.id.uzytkownik)
            uzytkownik.text = sb.toString() */
            binding.uzytkownik.text = sb.toString()
        }
    } catch (e: Exception) {
        withContext(Dispatchers.Main) {
            Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
        }
    }
}




/*
    //załadowanie bazy danych "uzytkownik" automatycznie
    private fun subscribeToRealtimeUpdates() {
        personCollectionName.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            firebaseFirestoreException?.let {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            querySnapshot?.let {
                val sb = StringBuilder()
                for (document in it) {
                    val person = document.toObject<users>()
                    sb.append("$person\n")
                }
                val imie = findViewById<TextView>(R.id.uzytkownik).apply {

                    text = "Witaj, " + sb.toString()
                }
            }
        }

    } */
}