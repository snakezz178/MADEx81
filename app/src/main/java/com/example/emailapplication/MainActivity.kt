package com.example.emailapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.EXTRA_EMAIL
import android.content.Intent.EXTRA_SUBJECT
import android.content.Intent.EXTRA_TEXT
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    @SuppressLint("IntentReset")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val recipientEditText =findViewById<EditText>(R.id.email)
        val subjectEditText =findViewById<EditText>(R.id.subject)
        val messageEditText=findViewById<EditText>(R.id.etmessage)
        val SendEmail=findViewById<Button>(R.id.send)

        SendEmail.setOnClickListener{
            val recipient= recipientEditText.text.toString().trim()
            val subject=subjectEditText.text.toString().trim()
            val message=messageEditText.text.toString().trim()

            val mIntent=Intent(Intent.ACTION_SEND)

            mIntent.data= "mailto:".toUri()
            mIntent.type ="text/plain"
            mIntent.putExtra(EXTRA_EMAIL, arrayOf(recipient))
            mIntent.putExtra(EXTRA_SUBJECT,subject)
            mIntent.putExtra(EXTRA_TEXT,message)
            try{
                startActivity(Intent.createChooser(mIntent,"Send Email"))
            }catch (e:Exception){
                Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }
}

