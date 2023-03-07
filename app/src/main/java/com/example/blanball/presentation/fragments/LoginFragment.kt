package com.example.blanball.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.blanball.R
import com.example.blanball.databinding.FragmentLoginBinding
import com.example.blanball.presentation.viewmodels.LoginViewModel
import com.example.domain.entity.LoginResultEntity
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment : Fragment() {

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
                        // Login success, handle data
                    }
                    is LoginResultEntity.Error -> {
                        // Login error, handle error message
                    }
                }
            }
        }
    }
}