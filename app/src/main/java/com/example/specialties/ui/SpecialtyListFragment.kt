package com.example.specialties.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.specialties.R
import com.example.specialties.model.Specialty
import com.example.specialties.ui.adapter.SpecialtyAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.specialty_list_fragment.*
import javax.inject.Inject

const val SPECIALTY_ID = "SP_ID"

class SpecialtyListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SpecialtyListViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.specialty_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSpecialties(::errorAction)
            .observe(
                this.viewLifecycleOwner
            ) { list -> if (list.isNotEmpty()) initList(list) }
    }

    private fun initList(specialties: List<Specialty>) {
        val adapter = SpecialtyAdapter(::onClick)
        adapter.submitList(specialties)
        recyclerView.adapter = adapter
    }

    private fun onClick(specialty: Specialty) = findNavController().navigate(
        R.id.employeeListFragment,
        bundleOf(Pair(SPECIALTY_ID, specialty.specialty_id))
    )

    private fun errorAction() {
        Toast.makeText(requireContext(), resources.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
    }
}