package com.max.roomdatabase.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.max.roomdatabase.R
import com.max.roomdatabase.data.Patient
import com.max.roomdatabase.data.PatientViewModel
import com.max.roomdatabase.data.SwipeToDelete
import com.max.roomdatabase.databinding.FragmentListBinding



class ListFragment : Fragment() {
    private lateinit var mPatientViewModel: PatientViewModel
    private var _binding:FragmentListBinding? = null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        //Use RecyclerView

        val adapter =ListAdapter()
        val recyclerView = _binding!!.rcView
        recyclerView.adapter =adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val swipeHandler = object : SwipeToDelete(requireContext()){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapters =recyclerView.adapter as ListAdapter
                adapters.deleteItem(viewHolder.adapterPosition)
            }

        }

        // Use PatientViewModel
        mPatientViewModel = ViewModelProvider(this).get(PatientViewModel::class.java)
        mPatientViewModel.readAllData.observe(viewLifecycleOwner, { patient ->
            adapter.setData(patient as ArrayList<Patient>)
        })

        binding.floatingActionButton.setOnClickListener {

            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }

        val ItemTouchHelper =ItemTouchHelper(swipeHandler)
        ItemTouchHelper.attachToRecyclerView(recyclerView)

        setHasOptionsMenu(true)
        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId ){
           R.id.menu_delete -> deleteAllPatients()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllPatients() {

        val builder = AlertDialog.Builder (requireContext())
        builder.setPositiveButton("Да"){_,_ ->
            mPatientViewModel.deleteAllPatients()

        }
        builder.setNegativeButton("Нет"){_,_ ->}
            builder.setTitle("Удалить ?")
            builder.setMessage("Вы хотите удалить всё? ")
            builder.create().show()
        }

    }




