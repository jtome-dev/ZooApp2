package com.example.zooapp2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.zooapp2.data.ZooRepository

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SELECTION = "extra_selection"
        const val EXTRA_SUBJECT = "extra_subject"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val tvDetailsTitle: TextView = findViewById(R.id.tvDetailsTitle)
        val listViewDetails: ListView = findViewById(R.id.listViewDetails)

        // Get values from intent extras
        val selection = intent.getStringExtra(EXTRA_SELECTION)
        val subject = intent.getStringExtra(EXTRA_SUBJECT)

        when (subject) {
            "Animal" -> {
                tvDetailsTitle.text = "Animal Details: $selection"
                val animalNames = ZooRepository.getAnimals().filter { it.type == selection }.map { it.name }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, animalNames)
                listViewDetails.adapter = adapter
            }
            "Habitat" -> {
                tvDetailsTitle.text = "Habitat Details: $selection"
                val habitatIds = ZooRepository.getHabitats().filter { it.name == selection }.map { it.id }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, habitatIds)
                listViewDetails.adapter = adapter
            }
        }
    }
}
