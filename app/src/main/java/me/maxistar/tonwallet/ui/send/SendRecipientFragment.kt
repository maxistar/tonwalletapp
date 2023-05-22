package me.maxistar.tonwallet.ui.send

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.databinding.FragmentSendRecipientBinding

class SendRecipientFragment : Fragment() {

    companion object {
        fun newInstance() = SendRecipientFragment()
    }


    private var binding: FragmentSendRecipientBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val root = inflater.inflate(R.layout.fragment_send_recipient, container, false)
        binding = FragmentSendRecipientBinding.inflate(inflater, container, false)

        val button = binding!!.button
        button.setOnClickListener({
            val fm: FragmentManager = parentFragmentManager
            fm.beginTransaction()
                .replace(R.id.container, SendAmountFragment.newInstance(binding!!.walletAddress.text.toString()))
                .addToBackStack(null)
                .commit()
        })

        return binding!!.root;
    }
}