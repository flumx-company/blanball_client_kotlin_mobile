package com.example.blanball.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.form
import com.example.blanball.R
import com.example.blanball.databinding.FragmentResetPasswordStep2Binding
import com.example.blanball.presentation.viewmodels.ResetPasswordStep2ViewModel
import com.example.blanball.utils.App
import com.example.blanball.utils.Utils
import com.example.domain.entity.EmailResetResultEntity
import com.example.domain.entity.SendCodeResultEntity
import javax.inject.Inject

class ResetPasswordStep2Fragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ResetPasswordStep2ViewModel.ResetPasswordStep2ViewModelFactory
    private lateinit var loadingFragment: LoadingFragment
    private lateinit var viewModel: ResetPasswordStep2ViewModel
    private var _binding: FragmentResetPasswordStep2Binding? = null
    private val binding get() = _binding!!
    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentResetPasswordStep2Binding.inflate(inflater, container, false)
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
            ViewModelProvider(this, viewModelFactory)[ResetPasswordStep2ViewModel::class.java]

        binding.resetPass.setOnClickListener {
            navigateToResetPassStep3()
        }
        binding.cancelButton.setOnClickListener {
            navigateToLoginScreen()
        }



        fun startTimer() {
            binding.resendButton.visibility = View.GONE
            binding.resendTimer.visibility = View.VISIBLE

            timer = object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsLeft = millisUntilFinished / 1000
                    binding.resendTimer.text = "Отправить повторно через $secondsLeft секунд"
                }

                override fun onFinish() {
                    binding.resendTimer.visibility = View.GONE
                    binding.resendButton.visibility = View.VISIBLE
                }
            }.start()
        }

        binding.resendButton.setOnClickListener {
            startTimer()
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
                length().atMost(255).description(getString(R.string.max_chars_error_email))
                length().atLeast(3).description(getString(R.string.min_chars_error_email))
                isEmail().description(getString(R.string.format_error_email))
            }
            submitWith(binding.resendButton) {
                keyboard.hideSoftInputFromWindow(view.windowToken, 0)
                loadingFragment.view?.visibility = View.VISIBLE
                viewModel.requestReset(it["Email"]?.value.toString())
            }
        }

        form{
            useRealTimeValidation()
            inputLayout(binding.editText1, "EditText1") {
                onValue { _, _ ->
                    binding.editText2.requestFocus()
                }
            }
            inputLayout(binding.editText2, "EditText1"){
                onValue { _, _ ->
                    binding.editText3.requestFocus()
                }
            }
            inputLayout(binding.editText3, "EditText1"){
                onValue { _, _ ->
                    binding.editText4.requestFocus()
                }
            }
            inputLayout(binding.editText4, "EditText1"){
                onValue { _, _ ->
                    binding.editText5.requestFocus()
                }
            }
            inputLayout(binding.editText5, "EditText1"){}
        }

            form {
                inputLayout(binding.editText1, "EditText1") {
                    isNotEmpty()
                }
                inputLayout(binding.editText2, "EditText2") {
                    isNotEmpty()
                }
                inputLayout(binding.editText3, "EditText3") {
                    isNotEmpty()
                }
                inputLayout(binding.editText4, "EditText4") {
                    isNotEmpty()
                }
                inputLayout(binding.editText5, "EditText5") {
                    isNotEmpty()
                }
                submitWith(binding.resetPass) {
                    keyboard.hideSoftInputFromWindow(view.windowToken, 0)
                    loadingFragment.view?.visibility = View.VISIBLE

                    val stringCode =
                        (it["EditText1"]?.value.toString() + it["EditText2"]?.value.toString() + it["EditText3"]?.value.toString() + it["EditText4"]?.value.toString() + it["EditText5"]?.value.toString())
                    viewModel.sendCode(stringCode)
                }
            }


        viewModel.requestResetResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is EmailResetResultEntity.Success -> {
                    loadingFragment.view?.visibility = View.GONE

                }
                is EmailResetResultEntity.Error -> {
                    loadingFragment.view?.visibility = View.GONE
                    binding.emailPlaceholder.error = getString(R.string.invalid_credential_error)
                }
            }
        }

        viewModel.sendCodeResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is SendCodeResultEntity.Success -> {
                    loadingFragment.view?.visibility = View.GONE
                    navigateToResetPassStep3()
                }
                is SendCodeResultEntity.Error -> {
                    loadingFragment.view?.visibility= View.GONE
                    binding.editText1.error = getString(R.string.invalid_credential_error)
                    binding.editText2.error = getString(R.string.invalid_credential_error)
                    binding.editText3.error = getString(R.string.invalid_credential_error)
                    binding.editText4.error = getString(R.string.invalid_credential_error)
                    binding.editText5.error = getString(R.string.invalid_credential_error)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}