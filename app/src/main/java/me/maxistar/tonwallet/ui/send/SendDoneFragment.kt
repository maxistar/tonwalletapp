package me.maxistar.tonwallet.ui.send

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.WalletActivity
import me.maxistar.tonwallet.databinding.FragmentSendDoneBinding
import me.maxistar.tonwallet.databinding.FragmentSendRecipientBinding
import me.maxistar.tonwallet.util.TonFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SendDoneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SendDoneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var recipient: String? = null
    private var amount: String? = null

    private var binding: FragmentSendDoneBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipient = it.getString(ARG_PARAM1)
            amount = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_send_done, container, false)
        binding = FragmentSendDoneBinding.inflate(inflater, container, false)

        val label = binding!!.tonWalletDescription
        label.text = getResources().getString(R.string.send_form__sent_description).format(amount, TonFormatter.addressShorten(recipient!!))

        val button = binding!!.button
        button.setOnClickListener({
            val intent = Intent(context, WalletActivity::class.java)
            startActivity(intent)
        })

        return binding!!.root;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SendDoneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SendDoneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}