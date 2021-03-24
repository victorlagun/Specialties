package com.example.specialties.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.specialties.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.specialty_list_fragment.*
import javax.inject.Inject

class SpecialtyListFragment : DaggerFragment() {

    companion object {
        fun newInstance() = SpecialtyListFragment()
    }

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
        viewModel.getEmployees()
            .observe(
                this.viewLifecycleOwner,
                { list -> if (list.isNotEmpty()) text.text = list.first().name })
    }
}