package com.example.tonwalletactivities.ui.screen6

import com.example.tonwalletactivities.databinding.FragmentScreen6Binding

import com.example.tonwalletactivities.ui.wallet.WalletViewModel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tonwalletactivities.databinding.FragmentScreen5Binding

class Screen6Fragment : Fragment() {

    private var _binding: FragmentScreen6Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(WalletViewModel::class.java)

        _binding = FragmentScreen6Binding.inflate(inflater, container, false)
        val root: View = binding.root

        // val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            // textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}