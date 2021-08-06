package com.max.roomdatabase.fragments.add

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.max.roomdatabase.R
import com.max.roomdatabase.data.Patient
import com.max.roomdatabase.data.PatientViewModel
import com.max.roomdatabase.databinding.FragmentAddBinding


class AddFragment : Fragment() {
    private lateinit var mPatientViewModel: PatientViewModel
    private var _binding:FragmentAddBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mPatientViewModel = ViewModelProvider(this).get(PatientViewModel::class.java)
        binding.addB.setOnClickListener {
            insertDataToDatabase()
        }
        setHasOptionsMenu(true)
        return binding.root

    }

    private fun insertDataToDatabase() {
        val firstName = binding.firstEdit.text.toString()
        val lastName = binding.lastEdit.text.toString()
        val age = binding.ageText.text
        val diagnosis = binding.digEdit.text.toString()

        if (inputCheck(firstName, lastName, age, diagnosis)) {
            val patient =
                Patient(0, firstName, lastName, Integer.parseInt(age.toString()), diagnosis)

            mPatientViewModel.addPatient(patient)

            Toast.makeText(requireContext(), "Пациент успешно добавлен", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheck(firstName: String, lastName: String, age: Editable, diagnosis: String):Boolean{
        return (!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !diagnosis.isEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }

}