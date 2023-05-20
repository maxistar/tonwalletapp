package me.maxistar.tonwallet.ui.restore_wallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import me.maxistar.tonwallet.AccessCodeActivity
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.model.MemoWords


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
        button.setOnClickListener {
            val intent = Intent(context, AccessCodeActivity::class.java)
            startActivity(intent)
        }

        val errorButton = root.findViewById<Button>(R.id.button_do_not_have)
        errorButton.setOnClickListener({
            val fm: FragmentManager = parentFragmentManager
            fm.beginTransaction()
                .replace(R.id.container, RestoreWalletFailureFragment.newInstance("",""))
                .addToBackStack(null)
                .commit()
        })

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
        setAdapterForControl(root, R.id.test_word4, adapter)
        setAdapterForControl(root, R.id.test_word5, adapter)
        setAdapterForControl(root, R.id.test_word6, adapter)
        setAdapterForControl(root, R.id.test_word7, adapter)
        setAdapterForControl(root, R.id.test_word8, adapter)
        setAdapterForControl(root, R.id.test_word9, adapter)
        setAdapterForControl(root, R.id.test_word10, adapter)
        setAdapterForControl(root, R.id.test_word11, adapter)
        setAdapterForControl(root, R.id.test_word12, adapter)
        setAdapterForControl(root, R.id.test_word13, adapter)
        setAdapterForControl(root, R.id.test_word14, adapter)
        setAdapterForControl(root, R.id.test_word15, adapter)
        setAdapterForControl(root, R.id.test_word16, adapter)
        setAdapterForControl(root, R.id.test_word17, adapter)
        setAdapterForControl(root, R.id.test_word18, adapter)
        setAdapterForControl(root, R.id.test_word19, adapter)
        setAdapterForControl(root, R.id.test_word20, adapter)
        setAdapterForControl(root, R.id.test_word21, adapter)
        setAdapterForControl(root, R.id.test_word22, adapter)
        setAdapterForControl(root, R.id.test_word23, adapter)
        setAdapterForControl(root, R.id.test_word24, adapter)

    }

    private fun setAdapterForControl(root: View, controlId: Int, adapter: ArrayAdapter<String>) {
        val actv = root.findViewById(controlId) as AutoCompleteTextView
        actv.threshold = 1
        actv.setAdapter(adapter)
    }

}