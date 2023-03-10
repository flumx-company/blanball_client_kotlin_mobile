package com.example.blanball.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.blanball.R
import com.example.blanball.databinding.FragmentLoginBinding
import com.example.blanball.presentation.viewmodels.LoginViewModel
import com.example.blanball.utils.App
import com.example.domain.entity.LoginResultEntity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: LoginViewModel.LoginViewModelFactory

    private lateinit var viewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.applicationContext as App).appComponent.inject(this)

        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView.visibility = View.GONE

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.signInBtn.setOnClickListener {
            val username = binding.loginPlaceholderEdit.text.toString()
            val userPassword = binding.passwordPlaceholderEdit.text.toString()
            viewModel.login(username, userPassword)

            viewModel.loginResult.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is LoginResultEntity.Success -> {
                        navigateToHomeScreen()
                    }
                    is LoginResultEntity.Error -> {

                    }
                }
            }
        }
    }

    fun navigateToHomeScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}