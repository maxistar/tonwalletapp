package com.example.tonwalletactivities.ui.restore_wallet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tonwalletactivities.R

class RestoreWalletFragment : Fragment() {

    companion object {
        fun newInstance() = RestoreWalletFragment()
    }

    private lateinit var viewModel: RestoreWalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RestoreWalletViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_restore_wallet, container, false)
    }

}