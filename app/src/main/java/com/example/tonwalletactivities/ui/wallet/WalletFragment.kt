package com.example.tonwalletactivities.ui.wallet

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.tonwalletactivities.CreateWalletActivity
import com.example.tonwalletactivities.R
import com.example.tonwalletactivities.ReceiveActivity
import com.example.tonwalletactivities.SendActivity

class WalletFragment : Fragment() {

    companion object {
        fun newInstance() = WalletFragment()
    }

    private lateinit var viewModel: WalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_wallet, container, false)

        val buttonSend = root.findViewById<Button>(R.id.button_send)
        buttonSend.setOnClickListener({
            val intent = Intent(this.context, SendActivity::class.java)
            startActivity(intent)
        })

        val buttonReceive = root.findViewById<Button>(R.id.button_receive)
        buttonReceive.setOnClickListener({
            val intent = Intent(this.context, ReceiveActivity::class.java)
            startActivity(intent)
        })

        return root;
    }

}