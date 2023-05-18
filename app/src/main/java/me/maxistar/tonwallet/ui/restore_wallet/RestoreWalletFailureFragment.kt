package me.maxistar.tonwallet.ui.restore_wallet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import me.maxistar.tonwallet.CreateWalletActivity
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.RestoreWalletActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RestoreWalletFailureFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestoreWalletFailureFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // FragmentRestoreWalletFailureBinding

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_restore_wallet_failure, container, false)

        val button = root.findViewById<Button>(R.id.button)
        button.setOnClickListener({
            val intent = Intent(this.context, RestoreWalletActivity::class.java)
            startActivity(intent)
        })

        val button_create_empty = root.findViewById<Button>(R.id.button_empty_wallet)
        button_create_empty.setOnClickListener({
            val intent = Intent(this.context, CreateWalletActivity::class.java)
            startActivity(intent)
        })

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RestoreWalletFailureFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestoreWalletFailureFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}