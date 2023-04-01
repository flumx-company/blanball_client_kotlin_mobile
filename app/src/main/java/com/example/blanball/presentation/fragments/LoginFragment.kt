package com.example.blanball.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.form
import com.example.blanball.R
import com.example.blanball.databinding.FragmentLoginBinding
import com.example.blanball.presentation.viewmodels.LoginViewModel
import com.example.blanball.utils.App
import com.example.blanball.utils.Utils
import com.example.domain.entity.LoginResultEntity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class LoginFragment : Fragment() {

    private lateinit var loadingFragment: LoadingFragment
    internal lateinit var viewModel: LoginViewModel
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

        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)

        bottomNavigationView.visibility = View.GONE

        loadingFragment = LoadingFragment.newInstance()

        activity?.supportFragmentManager?.beginTransaction()
            ?.hide(loadingFragment)
            ?.add(android.R.id.content, loadingFragment)?.commit()


        binding.registrationBtn.setOnClickListener {
            navigateToRegistration()
        }

        binding.dontRememberBtn.setOnClickListener {
            navigateToResetPassword()
        }

        val editTextArray = arrayOf(
            binding.emailPlaceholderEdit,
            binding.passwordPlaceholderEdit
        )

        Utils.animateEditTextFocus(editTextArray, binding.logo)

        val keyboard = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

//validation
        form {
            inputLayout(binding.emailPlaceholder, "Email") {
                isNotEmpty().description(getString(R.string.empty_field_error))
                length().atMost(255).description(getString(R.string.max_chars_error_email))
                length().atLeast(3).description(getString(R.string.min_chars_error_email))
                isEmail().description(getString(R.string.format_error_email))
            }
            inputLayout(binding.passwordPlaceholder, "Password") {
                isNotEmpty().description(getString(R.string.empty_field_error))
                length().atMost(68).description(getString(R.string.max_chars_error_pass))
                length().atLeast(8).description(getString(R.string.min_chars_error_pass))
            }
            submitWith(binding.signInBtn) {
                keyboard.hideSoftInputFromWindow(view.windowToken, 0)
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                loadingFragment.view?.visibility = View.VISIBLE
            }
        }

        //viewModel observes the result
        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is LoginResultEntity.Success -> {
                    loadingFragment.view?.visibility = View.GONE
                    navigateToHome()
                }
                is LoginResultEntity.Error -> {
                    loadingFragment.view?.visibility = View.GONE
                    binding.emailPlaceholder.error = getString(R.string.invalid_credential_error)
                    binding.passwordPlaceholder.error = getString(R.string.invalid_credential_error)
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

    fun navigateToResetPassword() {
        findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}