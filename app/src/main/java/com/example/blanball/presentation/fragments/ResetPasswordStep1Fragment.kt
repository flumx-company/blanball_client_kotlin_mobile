package com.example.blanball.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.form
import com.example.blanball.R
import com.example.blanball.databinding.FragmentResetPasswordStep1Binding
import com.example.blanball.presentation.viewmodels.ResetPasswordStep1ViewModel
import com.example.blanball.utils.Utils
import com.example.domain.entity.LoginResultEntity
import com.example.domain.entity.RequestResetResultEntity

class ResetPasswordStep1Fragment : Fragment() {


    lateinit var viewModelFactory: ResetPasswordStep1ViewModel.LoginViewModelFactory
    private lateinit var loadingFragment: LoadingFragment
    private lateinit var viewModel: ResetPasswordStep1ViewModel
    private var _binding: FragmentResetPasswordStep1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentResetPasswordStep1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingFragment = LoadingFragment.newInstance()

        activity?.supportFragmentManager?.beginTransaction()
            ?.hide(loadingFragment)
            ?.add(android.R.id.content, loadingFragment)?.commit()

        viewModel = ViewModelProvider(this, viewModelFactory)[ResetPasswordStep1ViewModel::class.java]

        binding.sendCodeBtn.setOnClickListener {
            navigateToResetPassStep2()
        }
        binding.cancelBtn.setOnClickListener {
            navigateToLoginScreen()
        }

        val editTextArray = arrayOf(
            binding.emailPlaceholderEdit,
            binding.editText1Edit,
            binding.editText2Edit,
            binding.editText3Edit,
            binding.editText4Edit,
            binding.editText5Edit,
        )

        Utils.animateEditTextFocus(editTextArray, binding.mainContainer)

        val keyboard =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        form {
            inputLayout(binding.emailPlaceholder, "Email") {
                isNotEmpty().description(getString(R.string.empty_field_error))
                length().atMost(255).description(getString(R.string.max_chars_error_email))
                length().atLeast(3).description(getString(R.string.min_chars_error_email))
                isEmail().description(getString(R.string.format_error_email))
            }
            submitWith(binding.sendCodeBtn) {
                keyboard.hideSoftInputFromWindow(view.windowToken, 0)
                loadingFragment.view?.visibility = View.VISIBLE
                viewModel.requestReset(it["Email"]?.value.toString())
            }
        }


        viewModel.requestResetResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResetResultEntity.Success -> {
                    loadingFragment.view?.visibility = View.GONE
                    navigateToResetPassStep2()
                }
                is LoginResultEntity.Error -> {
                    loadingFragment.view?.visibility = View.GONE
                    binding.emailPlaceholder.error = getString(R.string.invalid_credential_error)
                }
            }
        }
    }


    fun navigateToResetPassStep2() {
        findNavController().navigate(R.id.action_resetPasswordStep1Fragment_to_resetPasswordStep2Fragment)
    }

    fun navigateToLoginScreen() {
        findNavController().navigate(R.id.action_resetPasswordStep1Fragment_to_loginFragment2)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}