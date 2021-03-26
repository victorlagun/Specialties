package com.example.specialties.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.specialties.R
import com.example.specialties.model.Employee
import com.example.specialties.util.getAge
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_employee.view.*
import java.util.*

class EmployeeAdapter(private val onClick: (Employee) -> Unit) :
    ListAdapter<Employee, EmployeeAdapter.ViewHolder>(EmployeeDiffCallback) {

    class ViewHolder(view: View, val onClick: (Employee) -> Unit) : RecyclerView.ViewHolder(view) {
        private var currentEmployee: Employee? = null

        init {
            itemView.setOnClickListener { currentEmployee?.let { onClick(it) } }
        }

        fun bind(employee: Employee) {
            currentEmployee = employee
            with(itemView) {
                textFirstName.text =
                    resources.getString(
                        R.string.f_name,
                        employee.f_name?.toLowerCase(Locale.ROOT)?.capitalize(Locale.ROOT)
                    )
                textLastName.text =
                    resources.getString(
                        R.string.l_name,
                        employee.l_name?.toLowerCase(Locale.ROOT)?.capitalize(Locale.ROOT)
                    )
                textAge.text = if (!employee.birthday.isNullOrBlank()) resources.getString(
                    R.string.age,
                    getAge(employee.birthday)
                ) else "--"
                if (!employee.avatr_url.isNullOrBlank()) Picasso.get().load(employee.avatr_url)
                    .into(imageEmployee)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_employee, viewGroup, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

}

object EmployeeDiffCallback : DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.employee_id == newItem.employee_id
    }
}