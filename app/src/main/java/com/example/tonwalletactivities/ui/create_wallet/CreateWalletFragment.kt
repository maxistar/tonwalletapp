package com.example.tonwalletactivities.ui.create_wallet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tonwalletactivities.R

class CreateWalletFragment : Fragment() {

    companion object {
        fun newInstance() = CreateWalletFragment()
    }

    private lateinit var viewModel: CreateWalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateWalletViewModel::class.java)


        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_create_wallet, container, false)

        val textDescription: TextView = root.findViewById(R.id.ton_wallet_description)
        viewModel.text.observe(viewLifecycleOwner) {
            // textView.text = it
            textDescription.text = it
        }

        return root
    }

}