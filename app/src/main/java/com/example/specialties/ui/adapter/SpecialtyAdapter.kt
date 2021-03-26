package com.example.specialties.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.specialties.R
import com.example.specialties.model.Specialty
import kotlinx.android.synthetic.main.item_specialty.view.*

class SpecialtyAdapter(private val onClick: (Specialty) -> Unit) :
    ListAdapter<Specialty, SpecialtyAdapter.ViewHolder>(SpecialtyDiffCallback) {

    class ViewHolder(view: View, val onClick: (Specialty) -> Unit) : RecyclerView.ViewHolder(view) {
        private var currentSpecialty: Specialty? = null

        init {
            itemView.setOnClickListener { currentSpecialty?.let { onClick(it) } }
        }

        fun bind(specialty: Specialty) {
            currentSpecialty = specialty
            with(itemView) {
                textSpecialty.text = specialty.name
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_specialty, viewGroup, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

}

object SpecialtyDiffCallback : DiffUtil.ItemCallback<Specialty>() {
    override fun areItemsTheSame(oldItem: Specialty, newItem: Specialty): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Specialty, newItem: Specialty): Boolean {
        return oldItem.specialty_id == newItem.specialty_id
    }
}