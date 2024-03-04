package com.example.chemicalguessinggame

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.chemicalguessinggame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var chemicalsList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chemicalsList = mutableListOf("Gold")
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun buttonGuessOnClick(view: View) {
        binding.editTextAddChemical.text.clear()
        val chemicalName = binding.editTextChemicalName.text.toString()

        if (chemicalName.isNotBlank()) {
            val randomChemical = chemicalsList.random()
            val message: String

            if (randomChemical.equals(chemicalName, true)) {
                message = getString(R.string.chemical_guess_success, chemicalName)
                binding.imageViewChemical.setImageResource(R.drawable.right)
            }
            else {
                message = getString(R.string.chemical_guess_failed, randomChemical)
                binding.imageViewChemical.setImageResource(R.drawable.wrong)
            }

            binding.textViewMessage.text = message
        } else {
            binding.textViewMessage.text = getString(R.string.begin_message)
            binding.imageViewChemical.setImageResource(R.drawable.right)
            binding.editTextChemicalName.text.clear()
        }
    }

    fun buttonAddChemicalOnClick(view: View) {
        binding.editTextChemicalName.text.clear()
        val chemicalName = binding.editTextAddChemical.text.toString()

        if (chemicalName.isNotBlank()) {
            val chemicalExists = chemicalsList.any {
                it.equals(chemicalName, true)
            }

            val message = if (!chemicalExists) {
                chemicalsList.add(chemicalName)
                getString(R.string.chemical_added_successfully, chemicalName)
            } else {
                getString(R.string.chemical_already_available, chemicalName)
            }
            binding.textViewMessage.text = message
        }
        else {
            binding.textViewMessage.text = getString(R.string.begin_message)
            binding.editTextAddChemical.text.clear()
        }
    }
}
