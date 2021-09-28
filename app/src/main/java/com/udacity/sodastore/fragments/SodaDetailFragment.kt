package com.udacity.sodastore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.sodastore.R
import com.udacity.sodastore.viewmodels.SodaViewModel
import com.udacity.sodastore.databinding.FragmentSodaDetailBinding
import com.udacity.sodastore.models.Soda


class SodaDetailFragment : Fragment() {

    //We need this here because this fragment needs access to the SodaViewModel
    private val viewModel by activityViewModels<SodaViewModel>()

    //Create the property for the binding
    private lateinit var binding: FragmentSodaDetailBinding

    //We need to bind the soda object, so we initialize it here
    private val soda = Soda()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_soda_detail,
            container,
            false
        )

        // Bind the sodaObject. Without this, the soda data won't get carried to SodaListFragment
        binding.sodaObject = soda

        //Return the inflated layout
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigate back to SodaListFragment without adding a new soda
        binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(SodaDetailFragmentDirections.actionSodaDetailFragmentToSodaListFragment())
        }

        //This button saves the soda and navigates us back to SodaListFragment
        binding.saveButton.setOnClickListener { view: View ->

            //Add the newly made soda, calling upon the addSoda function in the SodaViewModel
            viewModel.addSoda(soda)

            //Navigate back to SodaListFragment
            view.findNavController()
                .navigate(SodaDetailFragmentDirections.actionSodaDetailFragmentToSodaListFragment())

        }
    }
}