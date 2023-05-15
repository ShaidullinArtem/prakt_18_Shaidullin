package com.example.listoftraffictickets

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView

class DisplayActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var createButton: Button
    private lateinit var deleteButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        listView = findViewById(R.id.list_view)
        createButton = findViewById(R.id.enter)
        deleteButton = findViewById(R.id.delete)

        val tickets = SharedPreferencesHelper.loadTickets(this)
        val ticketData = tickets.map { "${it.number} - ${it.type}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ticketData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        listView.adapter = adapter

        createButton.setOnClickListener {
            val intent = Intent(this, AddTicketActivity::class.java)
            startActivity(intent)
        }

        deleteButton.setOnClickListener {
            SharedPreferencesHelper.clearTickets(this)
            adapter.clear()
            adapter.notifyDataSetChanged()
        }

        val imageViewBackToMain: ImageView = findViewById(R.id.imageViewBackToMain)
        imageViewBackToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}