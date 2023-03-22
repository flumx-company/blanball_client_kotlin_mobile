package com.example.blanball.presentation.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.blanball.R
import com.example.blanball.presentation.viewmodels.LoadingViewModel

class LoadingFragment : Fragment() {

    companion object {
        fun newInstance() = LoadingFragment()
    }

    private lateinit var viewModel: LoadingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_loading, container, false)

        val loadingView = view.findViewById<ImageView>(R.id.loading_view)
        val animation = resources.getDrawable(R.drawable.animation_loading)

        loadingView.setImageDrawable(animation)
        (animation as AnimationDrawable).start()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoadingViewModel::class.java)
        // TODO: Use the ViewModel
    }
}