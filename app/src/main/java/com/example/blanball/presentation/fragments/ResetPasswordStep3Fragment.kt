package com.example.blanball.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.form
import com.example.blanball.R
import com.example.blanball.databinding.FragmentResetPasswordStep3Binding
import com.example.blanball.presentation.viewmodels.ResetPasswordStep3ViewModel
import com.example.blanball.utils.App
import com.example.blanball.utils.Utils
import com.example.domain.entity.ResetCompleteResultEntity
import javax.inject.Inject

class ResetPasswordStep3Fragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ResetPasswordStep3ViewModel.ResetPasswordStep3ViewModelFactory
    private lateinit var loadingFragment: LoadingFragment
    private lateinit var viewModel: ResetPasswordStep3ViewModel
    private var _binding: FragmentResetPasswordStep3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentResetPasswordStep3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.applicationContext as App).appComponent.inject(this)

        loadingFragment = LoadingFragment.newInstance()

        activity?.supportFragmentManager?.beginTransaction()
            ?.hide(loadingFragment)
            ?.add(android.R.id.content, loadingFragment)?.commit()

        viewModel =
            ViewModelProvider(this, viewModelFactory)[ResetPasswordStep3ViewModel::class.java]

        binding.cancelButton.setOnClickListener {
            navigateToLoginScreen()
        }

        val editTextArray = arrayOf(
            binding.passwordPlaceholderEdit,
            binding.repeatPasswordPlaceholderEdit
        )

        Utils.animateEditTextFocus(editTextArray, binding.mainContainer)

        val keyboard =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


        form {
            inputLayout(binding.passwordPlaceholder, "NewPass") {
                isNotEmpty().description(getString(R.string.empty_field_error))
                length().atMost(68).description(getString(R.string.max_chars_error_pass))
                length().atLeast(8).description(getString(R.string.min_chars_error_pass))
            }
            inputLayout(binding.repeatPasswordPlaceholder, "NewPassRepeat") {
                isNotEmpty().description(getString(R.string.empty_field_error))
                length().atMost(68).description(getString(R.string.max_chars_error_pass))
                length().atLeast(8).description(getString(R.string.min_chars_error_pass))
            }
            submitWith(binding.saveBtn) {
                keyboard.hideSoftInputFromWindow(view.windowToken, 0)
                loadingFragment.view?.visibility = View.VISIBLE
                viewModel.requestCompleteReset(it["NewPass"]?.value.toString())
            }
        }



        viewModel.requestResetCompleteResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResetCompleteResultEntity.Success -> {
                    loadingFragment.view?.visibility = View.GONE
                    navigateToLoginScreen()
                    Toast.makeText(requireContext(), getString(R.string.new_pass_toast), Toast.LENGTH_LONG).show()
                }
                is ResetCompleteResultEntity.Error -> {
                    loadingFragment.view?.visibility = View.GONE
                    binding.passwordPlaceholder.error = getString(R.string.invalid_credential_error)
                    binding.repeatPasswordPlaceholder.error = getString(R.string.invalid_credential_error)
                }
            }
        }
    }

    fun navigateToLoginScreen() {
        findNavController().navigate(R.id.action_resetPasswordStep3Fragment_to_loginFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}