package com.max.roomdatabase.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.max.roomdatabase.data.Patient
import com.max.roomdatabase.databinding.CustomRowBinding


class ListAdapter( ) :RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var patientList = ArrayList<Patient>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CustomRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }
    override fun getItemCount(): Int {
        return patientList.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = patientList[position]
        holder.binding.txFirstName.text = currentItem.firstName
        holder.binding.txLastName.text = currentItem.lastName
        holder.binding.txAge.text = currentItem.age.toString()
        holder.binding.txDiagnosis.text = currentItem.diagnosis

    }

    fun deleteItem(position: Int){
        patientList.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }



    class MyViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    fun setData(patient: ArrayList<Patient>) {
        this.patientList = patient

        notifyDataSetChanged()

    }




}