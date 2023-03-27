package com.example.blanball.presentation.fragments

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.blanball.R
import com.example.blanball.databinding.FragmentResetPasswordStep3Binding
import com.example.blanball.presentation.viewmodels.ResetPasswordStep3ViewModel

class ResetPasswordStep3Fragment : Fragment() {

    private var _binding: FragmentResetPasswordStep3Binding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ResetPasswordStep3ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentResetPasswordStep3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelButton.setOnClickListener {
            navigateToLoginScreen()
        }

       val editTextArray = arrayOf(
           binding.passwordPlaceholderEdit,
           binding.repeatPasswordPlaceholderEdit
       )

        var isFirstFocus = true

        for (i in editTextArray) {
            i.setOnFocusChangeListener{ _, hasFocus ->
                if (hasFocus && isFirstFocus){
                    val anim = ValueAnimator.ofInt(120, 0)
                    anim.duration = 1000
                    anim.addUpdateListener { valueAnimator ->
                        val layoutParams =
                            binding.mainContainer.layoutParams as ViewGroup.MarginLayoutParams
                        layoutParams.topMargin = valueAnimator.animatedValue as Int
                        binding.mainContainer.layoutParams = layoutParams
                    }
                    anim.start()
                    isFirstFocus = false
                }
            }
        }
    }

    fun navigateToLoginScreen() {
        findNavController().navigate(R.id.action_resetPasswordStep3Fragment_to_loginFragment)
    }
}