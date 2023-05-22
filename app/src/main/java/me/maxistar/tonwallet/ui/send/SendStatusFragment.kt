package me.maxistar.tonwallet.ui.send

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.WalletActivity
import me.maxistar.tonwallet.service.ServiceProvider

private const val ARG_PARAM1 = "recipient"
private const val ARG_PARAM2 = "amount"
private const val ARG_PARAM3 = "comment"

/**
 * A simple [Fragment] subclass.
 * Use the [SendStatusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SendStatusFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var recipient: String? = null
    private var amount: String? = null
    private var comment: String? = null

    private lateinit var viewModel: SendStatusViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipient = it.getString(ARG_PARAM1)
            amount = it.getString(ARG_PARAM2)
            comment = it.getString(ARG_PARAM3)
        }
        viewModel = ViewModelProvider(this).get(SendStatusViewModel::class.java)

        val settingsService = ServiceProvider.getSettingsService();

        viewModel.sendTransaction(
            settingsService.getWalletSecretPhrase(context!!),
            settingsService.getWalletVersion(context!!),
            settingsService.getTonConfiguration(context!!),
            recipient!!,
            amount!!.toDouble(),
            comment!!
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_send_status, container, false)

        val button = root.findViewById<Button>(R.id.button)
        button.setOnClickListener({
            val intent = Intent(context, WalletActivity::class.java)
            startActivity(intent)
        })

        viewModel.liveOperationStatus.observe(viewLifecycleOwner) {
            if (it !== "new") {
                val fm: FragmentManager = parentFragmentManager
                fm.beginTransaction()
                    .replace(R.id.container, SendDoneFragment.newInstance(recipient!!, amount!!))
                    .addToBackStack(null)
                    .commit()
            }
        }

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SendStatusFragment.
         */
        @JvmStatic
        fun newInstance(recipient: String, amount: String, comment: String) =
            SendStatusFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, recipient)
                    putString(ARG_PARAM2, amount)
                    putString(ARG_PARAM3, comment)
                }
            }
    }
}