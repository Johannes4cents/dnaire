package com.example.dnaire.fragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dnaire.R
import com.example.dnaire.camera.AgoraVideoChat
import com.example.dnaire.databinding.SignInBinding


class SignInFragment: Fragment() {
    lateinit var binding: SignInBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignInBinding.inflate(layoutInflater)
        clickListeners()
        return binding.root
    }

    fun clickListeners() {
        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_lobbyFragment)
        }

    }
}