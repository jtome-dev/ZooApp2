package com.example.zooapp2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.zooapp2.data.ZooRepository

class MainActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvPrompt: TextView
    private lateinit var btnAnimals: Button
    private lateinit var btnHabitats: Button
    private lateinit var listViewData: ListView
    private lateinit var btnAddNew: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        tvTitle = findViewById(R.id.tvTitle)
        tvPrompt = findViewById(R.id.tvPrompt)
        btnAnimals = findViewById(R.id.btnAnimals)
        btnHabitats = findViewById(R.id.btnHabitats)
        listViewData = findViewById(R.id.listViewData)
        btnAddNew = findViewById(R.id.btnAddNew)

        // Set up initial UI
        tvTitle.text = "Zoo Monitoring System"
        tvPrompt.text = "Please select a menu"

        // Set Animals button click listener
        btnAnimals.setOnClickListener {
            tvPrompt.text = "Please select an animal type"
            btnAddNew.text = "Add a New Animal"
            btnAddNew.visibility = View.VISIBLE

            // Get unique animal types in alphabetical order
            val animalTypes = ZooRepository.getAnimals().map { it.type }.distinct().sorted()

            // Display animal types in ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, animalTypes)
            listViewData.adapter = adapter

            // Set item click listener
            listViewData.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedAnimalType = animalTypes[position]
                navigateToDetailsActivity(selectedAnimalType, "Animal")
            }
        }

        // Set Habitats button click listener
        btnHabitats.setOnClickListener {
            tvPrompt.text = "Please select a habitat"
            btnAddNew.text = "Add a New Habitat"
            btnAddNew.visibility = View.VISIBLE

            // Get unique habitat names in alphabetical order
            val habitatNames = ZooRepository.getHabitats().map { it.name }.distinct().sorted()

            // Display habitat names in ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, habitatNames)
            listViewData.adapter = adapter

            // Set item click listener
            listViewData.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedHabitatName = habitatNames[position]
                navigateToDetailsActivity(selectedHabitatName, "Habitat")
            }
        }
    }
    private fun navigateToDetailsActivity(itemSelection: String, itemSubject: String) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EXTRA_SELECTION, itemSelection)
        intent.putExtra(DetailsActivity.EXTRA_SUBJECT, itemSubject)
        startActivity(intent)
    }
}

