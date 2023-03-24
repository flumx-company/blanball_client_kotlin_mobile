package com.example.blanball.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.blanball.R
import com.example.blanball.databinding.FragmentResetPasswordStep1Binding
import com.example.blanball.presentation.viewmodels.ResetPasswordStep1ViewModel

class ResetPasswordStep1Fragment : Fragment() {

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

        binding.sendCodeBtn.setOnClickListener{
            navigateToResetPassStep2()
        }
        binding.cancelBtn.setOnClickListener{
            navigateToLoginScreen()
        }
    }

    fun navigateToResetPassStep2() {
        findNavController().navigate(R.id.action_resetPasswordStep1Fragment_to_resetPasswordStep2Fragment)
    }

    fun navigateToLoginScreen() {
        findNavController().navigate(R.id.action_resetPasswordStep1Fragment_to_loginFragment2)
    }

}