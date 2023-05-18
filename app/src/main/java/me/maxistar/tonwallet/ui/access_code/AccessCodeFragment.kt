package me.maxistar.tonwallet.ui.access_code

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.WalletActivity

class AccessCodeFragment : Fragment() {

    companion object {
        fun newInstance() = AccessCodeFragment()
    }

    private lateinit var viewModel: AccessCodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccessCodeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_access_code, container, false)

        val button = root.findViewById<Button>(R.id.access_code_button_0)
        button.setOnClickListener({
            val intent = Intent(context, WalletActivity::class.java)
            startActivity(intent)
        })

        return root;
    }

}