package me.maxistar.tonwallet.ui.create_wallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import me.maxistar.tonwallet.AccessCodeActivity
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.model.MemoWords
import me.maxistar.tonwallet.service.ServiceProvider

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateWalletCheckFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateWalletCheckFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var seed: String? = null
    private var address: String? = null

    //private lateinit var seed: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            seed = it.getString(ARG_PARAM1)
            address = it.getString(ARG_PARAM2)
        }
        //viewModel = ViewModelProvider(this).get(CreateWalletViewModel::class.java)
        //Log.w("viewModel", viewModel.newWalletAddress)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_create_wallet_check, container, false)

        val settings = ServiceProvider.getSettingsService();

        val button = root.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            Log.w("address", address!!)
            Log.w("seed", seed!!)
            settings.storeWallet(context!!, address!!, seed!!)

            val intent = Intent(context, AccessCodeActivity::class.java)
            startActivity(intent)
        };

        setupAutosuggestions(context!!, root)

        return root;
    }


    private fun setupAutosuggestions(context: Context, root: View) {
        val words = MemoWords()
        val fruits = words.getWords()
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(context, android.R.layout.select_dialog_item, fruits)

        setAdapterForControl(root, R.id.test_word1, adapter)
        setAdapterForControl(root, R.id.test_word2, adapter)
        setAdapterForControl(root, R.id.test_word3, adapter)
    }

    private fun setAdapterForControl(root: View, controlId: Int, adapter: ArrayAdapter<String>) {
        val actv = root.findViewById(controlId) as AutoCompleteTextView
        actv.threshold = 1
        actv.setAdapter(adapter)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateWalletCheckFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(seed: String, address: String) =
            CreateWalletCheckFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, seed)
                    putString(ARG_PARAM2, address)
                }
            }
    }
}