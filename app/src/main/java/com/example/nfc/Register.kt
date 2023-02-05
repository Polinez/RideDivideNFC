package com.example.nfc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.nfc.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class Register : AppCompatActivity() {

    private val personCollectionName = Firebase.firestore.collection("uzytkownik")

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var  firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val imie = binding.etImie.text.toString()
            val email = binding.emailR.text.toString()
            val pass = binding.passR.text.toString()
            val confirmPass = binding.Haslo2R.text.toString()
            val user = users(imie)
            saveUsers(user)


            if (imie.isNotEmpty() &&email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent=Intent(this,loginActivity::class.java)
                            startActivity(intent)}
                        else{
                            val toast = Toast.makeText(this, "cos zle wpisałes", Toast.LENGTH_SHORT)
                            toast.show()
                            }
                    }
                }else{
                    val toast = Toast.makeText(this, "Haslo jest niepoprawne", Toast.LENGTH_SHORT)
                    toast.show()
                }
                }else{
                val toast = Toast.makeText(this, "Uzupełnij wszystkie pola", Toast.LENGTH_SHORT)
                toast.show()
                }
        }
    }
    //Cloud firebase Witaj,imie poprzez guzik rejestracji
    private fun saveUsers(users: users) = CoroutineScope(Dispatchers.IO).launch {
        try {
            personCollectionName.add(users).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, "Imie zostało dodane do DB", Toast.LENGTH_LONG)
                    .show()
            }
        } catch (e:Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}