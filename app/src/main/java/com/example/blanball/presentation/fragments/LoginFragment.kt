package com.example.blanball.presentation.fragments

import android.os.Bundle
import android.util.Patterns
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

        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        binding.signInBtn.setOnClickListener {
            val email = binding.loginPlaceholderEdit.text.toString().trim()
            val userPassword = binding.passwordPlaceholderEdit.text.toString().trim()

            val emailPattern = Patterns.EMAIL_ADDRESS
            val isValidEmail = emailPattern.matcher(email).matches()

            binding.registrationBtn.setOnClickListener {
                navigateToRegistration()
            }

            when {
                email.isEmpty() -> {
                    binding.loginPlaceholder.error = "Поле не може бути незаповненним"
                    return@setOnClickListener
                }
                userPassword.isEmpty() -> {
                    binding.passwordPlaceholder.error = "Поле не може бути незаповненним"
                    return@setOnClickListener
                }
                email.length > 255 -> {
                    binding.loginPlaceholder.error =
                        "Кількість символів не може бути більшою ніж 255"
                    return@setOnClickListener
                }
                userPassword.length < 8 -> {
                    binding.passwordPlaceholder.error =
                        "Кількість символів не може бути меншою ніж 8"
                    return@setOnClickListener
                }
                userPassword.length > 68 -> {
                    binding.passwordPlaceholder.error =
                        "Кількість символів не може бути більшою ніж 68"
                    return@setOnClickListener
                }
                !isValidEmail -> {
                    binding.loginPlaceholder.error = "Неправильний формат email"
                    return@setOnClickListener
                }
                else -> viewModel.login(email, userPassword)
            }

            viewModel.loginResult.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is LoginResultEntity.Success -> {
                        navigateToHome()
                    }
                    is LoginResultEntity.Error -> {

                    }
                }
            }
        }
    }


    fun navigateToHome() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    fun navigateToRegistration() {
        findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}