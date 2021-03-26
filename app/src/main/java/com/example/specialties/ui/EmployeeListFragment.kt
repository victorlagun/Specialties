package com.example.specialties.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.specialties.R
import com.example.specialties.model.Employee
import com.example.specialties.ui.adapter.EmployeeAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.employee_list_fragment.*
import javax.inject.Inject

const val EMPLOYEE_ID = "EMP_ID"

class EmployeeListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: EmployeeListViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.employee_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEmployees(arguments?.get(SPECIALTY_ID) as Int)
            .observe(
                this.viewLifecycleOwner
            ) { list -> if (list.isNotEmpty()) initList(list) }
    }

    private fun initList(employees: List<Employee>) {
        val adapter = EmployeeAdapter(::onClick)
        adapter.submitList(employees)
        recyclerView.adapter = adapter
    }

    private fun onClick(employee: Employee) = findNavController().navigate(
        R.id.employeeFragment,
        bundleOf(Pair(EMPLOYEE_ID, employee.employee_id))
    )
}