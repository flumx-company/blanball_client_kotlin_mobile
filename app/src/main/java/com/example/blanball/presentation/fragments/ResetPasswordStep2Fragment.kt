package com.example.blanball.presentation.fragments

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.blanball.R
import com.example.blanball.databinding.FragmentResetPasswordStep2Binding
import com.example.blanball.presentation.viewmodels.ResetPasswordStep2ViewModel

class ResetPasswordStep2Fragment : Fragment() {

    private var _binding: FragmentResetPasswordStep2Binding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ResetPasswordStep2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentResetPasswordStep2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resetPass.setOnClickListener {
            navigateToResetPassStep3()
        }
        binding.cancelButton.setOnClickListener {
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

    fun navigateToResetPassStep3() {
        findNavController().navigate(R.id.action_resetPasswordStep2Fragment_to_resetPasswordStep3Fragment)
    }

    fun navigateToLoginScreen() {
        findNavController().navigate(R.id.action_resetPasswordStep2Fragment_to_loginFragment3)
    }
}