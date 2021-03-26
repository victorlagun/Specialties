package com.example.specialties.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.specialties.R
import com.example.specialties.model.Employee
import com.example.specialties.model.Specialty
import com.example.specialties.ui.adapter.SpecialtyAdapter
import com.example.specialties.util.formatDate
import com.example.specialties.util.getAge
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.employee_fragment.*
import kotlinx.android.synthetic.main.item_employee.imageEmployee
import kotlinx.android.synthetic.main.item_employee.textAge
import kotlinx.android.synthetic.main.item_employee.textFirstName
import kotlinx.android.synthetic.main.item_employee.textLastName
import java.util.*
import javax.inject.Inject

class EmployeeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: EmployeeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.employee_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEmployee(arguments?.get(EMPLOYEE_ID) as Int)
            .observe(
                this.viewLifecycleOwner
            ) { initUi(it) }
        viewModel.getSpecialties(arguments?.get(EMPLOYEE_ID) as Int)
            .observe(
                this.viewLifecycleOwner
            ) { list -> if (list.isNotEmpty()) initList(list) }
    }

    private fun initUi(employee: Employee) {
        if (!employee.avatr_url.isNullOrBlank()) Picasso.get().load(employee.avatr_url)
            .into(imageEmployee)
        textFirstName.text = resources.getString(
            R.string.f_name, employee.f_name?.toLowerCase(
                Locale.ROOT
            )?.capitalize(Locale.ROOT)
        )
        textLastName.text = resources.getString(
            R.string.l_name, employee.l_name?.toLowerCase(
                Locale.ROOT
            )?.capitalize(Locale.ROOT)
        )
        textBirthday.text = if (!employee.birthday.isNullOrBlank()) resources.getString(
            R.string.birthday,
            formatDate(employee.birthday)
        ) else "--"
        textAge.text = if (!employee.birthday.isNullOrBlank()) resources.getString(
            R.string.age,
            getAge(employee.birthday)
        ) else "--"
        textSpecialty.text = resources.getString(
            R.string.specialty
        )

    }

    private fun initList(specialties: List<Specialty>) {
        val adapter = SpecialtyAdapter(::onClick)
        adapter.submitList(specialties)
        recyclerView.adapter = adapter
    }

    private fun onClick(specialty: Specialty) {}
}