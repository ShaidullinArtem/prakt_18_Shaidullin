package com.example.listoftraffictickets

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class AddTicketActivity : AppCompatActivity() {

    private lateinit var numberEditText: EditText
    private lateinit var typeEditText: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ticket)

        numberEditText = findViewById(R.id.number_edit_text)
        typeEditText = findViewById(R.id.type_edit_text)

        val saveButton: Button = findViewById(R.id.add)
        saveButton.setOnClickListener {
            if (numberEditText.text.toString().isEmpty() || typeEditText.text.toString().isEmpty()){
                val i = Toast.makeText(this, "Введите номер и тип билета.", Toast.LENGTH_SHORT)
                i.setGravity(Gravity.TOP, 0, 160)
                i.show()
                return@setOnClickListener
            }

            val number = numberEditText.text.toString()
            val type = typeEditText.text.toString()
            val ticket = Ticket(number, type)
            val tickets = SharedPreferencesHelper.loadTickets(this).toMutableList()
            tickets.add(ticket)
            SharedPreferencesHelper.saveTickets(this, tickets)

            val i = Toast.makeText(this, "Билет № $number добавлен.", Toast.LENGTH_SHORT)
            i.setGravity(Gravity.TOP, 0, 160)
            i.show()

            numberEditText.text = null
            typeEditText.text = null
        }

        val returnImageView: ImageView = findViewById(R.id.imageViewBack)
        returnImageView.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            startActivity(intent)
        }
    }
}