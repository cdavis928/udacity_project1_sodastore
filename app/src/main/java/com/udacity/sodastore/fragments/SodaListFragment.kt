package com.udacity.sodastore.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.sodastore.R
import com.udacity.sodastore.viewmodels.SodaViewModel
import com.udacity.sodastore.databinding.SodaListFragmentBinding
import com.udacity.sodastore.models.Soda
import kotlinx.android.synthetic.main.fragment_soda_detail.view.*
import kotlinx.android.synthetic.main.soda_list_item.view.*

class SodaListFragment : Fragment() {

    //We need this here because this fragment needs access to the SodaViewModel
    private val viewModel by activityViewModels<SodaViewModel>()

    private lateinit var binding: SodaListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.soda_list_fragment,
            container,
            false
        )

        //Call the observer function
        observe()

        //Use this to create the menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addSodaButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(SodaListFragmentDirections.actionSodaListFragmentToSodaDetailFragment())
        }

    }

    //We use this to inflate the overflow menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    // The items in your overflow menu will not be functional unless you include this
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    //This function observes the sodaList LiveData, from an ArrayList
    private fun observe() {
        viewModel.sodaList.observe(viewLifecycleOwner, { sodas ->
            sodas.forEach { newLayout(it) }
        })
    }

    //Soda objects get passed into here, their attributes are added to their respective text views in soda_list_item
    private fun newLayout(soda: Soda) {
        val sodaItemLayout =
            layoutInflater.inflate(R.layout.soda_list_item, binding.sodaListItem, false)

        sodaItemLayout.soda_name_list_item.text = soda.name
        sodaItemLayout.soda_company_list_item.text = soda.company
        sodaItemLayout.soda_flavor_list_item.text = soda.flavor
        sodaItemLayout.soda_description_list_item.text = soda.description
        binding.sodaListItem.addView(sodaItemLayout)
    }

}



