package com.udacity.sodastore.ui.login

import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.udacity.sodastore.databinding.FragmentLoginDestinationBinding

class LoginDestination : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginDestinationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginDestinationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        binding.login.setOnClickListener { view: View ->
            view.findNavController().navigate(LoginDestinationDirections.actionLoginDestinationToWelcomeFragment())
        }

        binding.register.setOnClickListener { view: View ->
            view.findNavController().navigate(LoginDestinationDirections.actionLoginDestinationToWelcomeFragment())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}