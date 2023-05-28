package me.maxistar.tonwallet.ui.send

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.databinding.FragmentSendAmountBinding
import me.maxistar.tonwallet.util.TonFormatter

private const val ARG_PARAM1 = "recipient"

/**
 * A simple [Fragment] subclass.
 * Use the [SendAmountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SendAmountFragment : Fragment() {
    private var recipient: String? = null

    private var binding: FragmentSendAmountBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipient = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendAmountBinding.inflate(inflater, container, false)
        val fm: FragmentManager = parentFragmentManager

        val addressLabel = binding!!.addressLabel
        addressLabel.text = TonFormatter.addressShorten(recipient!!)

        val edit_button = binding!!.editAddressButton
        edit_button.setOnClickListener {
            fm.beginTransaction()
                .replace(
                    R.id.container, SendRecipientFragment.newInstance(recipient!!)
                )
                .addToBackStack(null)
                .commit()
        }

        val button = binding!!.button
        button.setOnClickListener {

            fm.beginTransaction()
                .replace(
                    R.id.container, SendCommentFragment.newInstance(
                        recipient!!,
                        binding!!.tonAmount.text.toString(),
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        return binding!!.root;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 2.
         * @return A new instance of fragment SendAmountFragment.
         */
        @JvmStatic
        fun newInstance(param1: String) =
            SendAmountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}