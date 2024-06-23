package com.example.zooapp2.data

import java.util.*

object ZooRepository {
    private val animalsMap: MutableMap<UUID, Animal> = mutableMapOf()
    private val habitatsMap: MutableMap<UUID, Habitat> = mutableMapOf()

    // Initialize some mock data
    init {
        // Mock Animals
        addAnimal(Animal(UUID.randomUUID(), "Lion", "Leo", 5, "Cut on left front paw", "2x daily"))
        addAnimal(Animal(UUID.randomUUID(), "Tiger", "Maj", 15, "None", "3x daily"))
        addAnimal(Animal(UUID.randomUUID(), "Bear", "Baloo", 1, "None", "None"))
        addAnimal(Animal(UUID.randomUUID(), "Giraffe", "Spots", 12, "None", "Grazing"))
        addAnimal(Animal(UUID.randomUUID(), "Lion", "Jeff", 4, "None", "None"))


        // Mock Habitats
        addHabitat(Habitat(UUID.randomUUID(), "Tundra","Penguin", "Freezing", false, "Fish running low", true, "Passed", false))
        addHabitat(Habitat(UUID.randomUUID(), "Rainforest","Bird", "Freezing", false, "Fish running low", true, "Passed", false))
        addHabitat(Habitat(UUID.randomUUID(), "Marine","Aquarium", "Freezing", false, "Fish running low", true, "Passed", false))
    }

    fun addAnimal(animal: Animal) {
        animalsMap[animal.id] = animal
    }

    fun addHabitat(habitat: Habitat) {
        habitatsMap[habitat.id] = habitat
    }

    fun getAnimals(): List<Animal> {
        return animalsMap.values.toList()
    }

    fun getHabitats(): List<Habitat> {
        return habitatsMap.values.toList()
    }
}
