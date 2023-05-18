package com.example.tonwalletactivities.ui.send

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.tonwalletactivities.R

class SendRecipientFragment : Fragment() {

    companion object {
        fun newInstance() = SendRecipientFragment()
    }

    private lateinit var viewModel: SendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SendViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_send_recipient, container, false)

        val button = root.findViewById<Button>(R.id.button)
        button.setOnClickListener({
            val fm: FragmentManager = parentFragmentManager
            fm.beginTransaction()
                .replace(R.id.container, SendAmountFragment.newInstance("", ""))
                .addToBackStack(null)
                .commit()
        })

        return root;
    }

}