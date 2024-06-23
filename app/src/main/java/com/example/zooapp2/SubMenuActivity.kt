package com.example.zooapp2

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.zooapp2.data.ZooRepository

class SubMenuActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SELECTION = "extra_selection"
        const val EXTRA_SUBJECT = "extra_subject"
    }

    private lateinit var tvDetailsTitle: TextView
    private lateinit var listViewDetails: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_menu)

        tvDetailsTitle = findViewById(R.id.tvDetailsTitle)
        listViewDetails = findViewById(R.id.listViewDetails)

        val selection = intent.getStringExtra(EXTRA_SELECTION) ?: return
        val subject = intent.getStringExtra(EXTRA_SUBJECT) ?: return

        when (subject) {
            "Animal" -> {
                tvDetailsTitle.text = "Please select an available $selection"
                val animalDetails = ZooRepository.getAnimals()
                    .filter { it.type == selection }
                    .map { it.name }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, animalDetails)
                listViewDetails.adapter = adapter
                listViewDetails.setOnItemClickListener { _, _, position, _ ->
                    val selectedAnimalName = animalDetails[position]
                    val selectedAnimal = ZooRepository.getAnimals().find { it.name == selectedAnimalName }
                    selectedAnimal?.let {
                        val intent = Intent(this, AnimalDetailsActivity::class.java)
                        intent.putExtra(AnimalDetailsActivity.EXTRA_ANIMAL_UUID, it.id.toString())
                        startActivity(intent)
                    }
                }
            }
            "Habitat" -> {
                tvDetailsTitle.text = "Please select an available $selection habitat"
                val habitatDetails = ZooRepository.getHabitats()
                    .filter { it.type == selection }
                    .map { it.name }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, habitatDetails)
                listViewDetails.adapter = adapter
                listViewDetails.setOnItemClickListener { _, _, position, _ ->
                    val selectedHabitatName = habitatDetails[position]
                    val selectedHabitat = ZooRepository.getHabitats().find { it.name == selectedHabitatName }
                    selectedHabitat?.let {
                        val intent = Intent(this, HabitatDetailsActivity::class.java)
                        intent.putExtra(HabitatDetailsActivity.EXTRA_HABITAT_UUID, it.id.toString())
                        startActivity(intent)
                    }
                }
            }
        }
    }
}
