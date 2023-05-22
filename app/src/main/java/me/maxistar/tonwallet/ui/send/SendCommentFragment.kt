package me.maxistar.tonwallet.ui.send

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.databinding.FragmentSendAmountBinding
import me.maxistar.tonwallet.databinding.FragmentSendCommentBinding
import me.maxistar.tonwallet.util.TonFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "recipient"
private const val ARG_PARAM2 = "amount"

/**
 * A simple [Fragment] subclass.
 * Use the [SendCommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SendCommentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var recipient: String? = null
    private var amount: String? = null

    private var binding: FragmentSendCommentBinding? = null


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
    ): View {

        binding = FragmentSendCommentBinding.inflate(inflater, container, false)

        val recipientLabel = binding!!.recipientValue
        recipientLabel.text = TonFormatter.addressShorten(recipient!!)

        val amountValue = binding!!.amountTitleValue
        amountValue.text = amount

        val button = binding!!.button
        button.setOnClickListener({
            val fm: FragmentManager = parentFragmentManager
            fm.beginTransaction()
                .replace(R.id.container, SendStatusFragment.newInstance(
                    recipient!!,
                    amount!!,
                    binding!!.comment.text.toString()
                ))
                .addToBackStack(null)
                .commit()
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
         * @return A new instance of fragment SendCommentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(recipient: String, amount: String) =
            SendCommentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, recipient)
                    putString(ARG_PARAM2, amount)
                }
            }
    }
}