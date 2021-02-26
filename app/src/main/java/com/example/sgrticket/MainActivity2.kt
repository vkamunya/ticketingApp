package com.example.sgrticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.sgrticket.databinding.ActivityMain2Binding
private lateinit var binding: ActivityMain2Binding
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val button=findViewById<Button>(R.id.LoginBtn)
        button.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val signup=findViewById<TextView>(R.id.SignUpText)
        signup.setOnClickListener {
            val intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }

    }

}