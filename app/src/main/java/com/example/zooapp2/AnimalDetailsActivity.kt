package com.example.zooapp2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.zooapp2.data.Animal
import com.example.zooapp2.data.ZooRepository
import java.util.UUID

class AnimalDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ANIMAL_UUID = "extra_animal_uuid"
    }

    private lateinit var tvId: TextView
    private lateinit var etType: EditText
    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var etHealth: EditText
    private lateinit var etFood: EditText
    private lateinit var btnEditSubmit: Button
    private lateinit var btnDelete: Button

    private var animal: Animal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_details)

        // Initialize views
        tvId = findViewById(R.id.tvId)
        etType = findViewById(R.id.etType)
        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        etHealth = findViewById(R.id.etHealth)
        etFood = findViewById(R.id.etFood)
        btnEditSubmit = findViewById(R.id.btnEditSubmit)
        btnDelete = findViewById(R.id.btnDelete)

        // Set title
        title = "Animal Details"

        // Check if animal UUID is passed via intent extras
        val animalUUIDString = intent.getStringExtra(EXTRA_ANIMAL_UUID)
        if (animalUUIDString != null) {
            val animalUUID = UUID.fromString(animalUUIDString)
            animal = ZooRepository.getAnimal(animalUUID)
        }

        // Populate UI with animal data if available
        animal?.let {
            tvId.text = "ID: ${it.id}"
            etType.setText(it.type)
            etName.setText(it.name)
            etAge.setText(it.age.toString())
            etHealth.setText(it.health)
            etFood.setText(it.food)
            // Enable the delete button if an animal is present
            btnDelete.isEnabled = true
        }

        // Set click listeners
        btnEditSubmit.setOnClickListener {
            if (animal != null) {
                updateAnimal()
            } else {
                saveAnimal()
            }
        }

        btnDelete.setOnClickListener {
            deleteAnimal()
        }
    }

    private fun updateAnimal() {
        animal?.let {
            it.type = etType.text.toString().trim()
            it.name = etName.text.toString().trim()
            it.age = etAge.text.toString().toIntOrNull() ?: 0
            it.health = etHealth.text.toString().trim()
            it.food = etFood.text.toString().trim()
            ZooRepository.updateAnimal(it)
        }
        finish()
    }

    private fun saveAnimal() {
        val type = etType.text.toString().trim()
        val name = etName.text.toString().trim()
        val age = etAge.text.toString().toIntOrNull() ?: 0
        val health = etHealth.text.toString().trim()
        val food = etFood.text.toString().trim()
        val newAnimal = Animal(UUID.randomUUID(), type, name, age, health, food)
        ZooRepository.addAnimal(newAnimal)
        finish()
    }

    private fun deleteAnimal() {
        animal?.let {
            ZooRepository.deleteAnimal(it.id)
        }
        finish()
    }
}