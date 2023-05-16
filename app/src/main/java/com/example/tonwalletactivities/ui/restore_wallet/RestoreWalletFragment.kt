package com.example.tonwalletactivities.ui.restore_wallet

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.tonwalletactivities.AccessCodeActivity
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
        val root = inflater.inflate(R.layout.fragment_restore_wallet, container, false)
        val button = root.findViewById<Button>(R.id.button)
        button.setOnClickListener({
            val intent = Intent(context, AccessCodeActivity::class.java)
            startActivity(intent)
        })

        val errorButton = root.findViewById<Button>(R.id.button_do_not_have)
        errorButton.setOnClickListener({
            val fm: FragmentManager = parentFragmentManager
            fm.beginTransaction()
                .replace(R.id.container, RestoreWalletFailureFragment.newInstance("",""))
                .addToBackStack(null)
                .commit()
        })
        return root;
    }

}