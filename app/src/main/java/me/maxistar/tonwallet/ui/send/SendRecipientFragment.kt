package me.maxistar.tonwallet.ui.send

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.SendActivity
import me.maxistar.tonwallet.databinding.FragmentSendRecipientBinding

private const val ARG_PARAM1 = "recipient"

class SendRecipientFragment : Fragment() {

    private var recipient: String? = null


    companion object {
        fun newInstance(param1: String) = SendRecipientFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
            }
        }
    }


    private var binding: FragmentSendRecipientBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipient = it.getString(ARG_PARAM1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendRecipientBinding.inflate(inflater, container, false)

        val recipientEditor = binding!!.walletAddress
        recipientEditor.setText(recipient!!)

        val button = binding!!.button
        button.setOnClickListener {
            val fm: FragmentManager = parentFragmentManager
            fm.beginTransaction()
                .replace(
                    R.id.container,
                    SendAmountFragment.newInstance(recipientEditor.text.toString())
                )
                .addToBackStack(null)
                .commit()
        }

        val scanButton = binding!!.scanAddressButton
        scanButton.setOnClickListener {
            val intent = Intent(context, SendActivity::class.java)
            intent.putExtra("scan", true)
            startActivity(intent)
        }

        return binding!!.root
    }
}