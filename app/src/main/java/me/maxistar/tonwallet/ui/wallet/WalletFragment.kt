package me.maxistar.tonwallet.ui.wallet

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.ReceiveActivity
import me.maxistar.tonwallet.SendActivity
import me.maxistar.tonwallet.model.TransactionItem
import me.maxistar.tonwallet.service.ServiceProvider
import me.maxistar.tonwallet.util.TonFormatter
import java.util.*
import kotlin.collections.ArrayList


class WalletFragment : Fragment() {

    companion object {
        fun newInstance() = WalletFragment()
    }

    private lateinit var viewModel: WalletViewModel

    // var values: ArrayList<TransactionItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settingsService = ServiceProvider.getSettingsService();

        viewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
        viewModel.updateWallet(
            settingsService.getWalletSecretPhrase(context!!),
            settingsService.getWalletVersion(context!!),
            settingsService.getTonConfiguration(context!!)
        )
        //values = ArrayList(3)
        //values!!.add(TransactionItem(10, "comment", "address"))
        //values!!.add(TransactionItem(10, "comment", "address"))
        //values!!.add(TransactionItem(10, "comment", "address"))

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
        buttonReceive.setOnClickListener {
            val intent = Intent(this.context, ReceiveActivity::class.java)
            startActivity(intent)
        }
        val textView = root.findViewById<TextView>(R.id.wallet_address_balance)
        viewModel.balance.observe(viewLifecycleOwner) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.setText(
                    Html.fromHtml(TonFormatter.nanoTonsToHtmlString(it), Html.FROM_HTML_MODE_COMPACT),
                    TextView.BufferType.SPANNABLE
                )
            } else {
                textView.setText(TonFormatter.nanoTonsToString(it))
            }
        }

        val listView = root.findViewById<ListView>(R.id.transactions)
        listView.adapter =
            viewModel.transactions.let { it.value?.let { it1 -> TransactionsAdapter(context!!, R.layout.transaction_item, it1.toList()) } }

        listView.emptyView = root.findViewById(R.id.no_transactions)

        viewModel.transactions.observe(viewLifecycleOwner) {
            (listView.adapter as TransactionsAdapter?)?.notifyDataSetChanged()
        }

        return root;
    }


    protected class TransactionsAdapter(context: Context, textViewResourceId: Int, values_: List<TransactionItem>) :
        ArrayAdapter<TransactionItem?>(context, textViewResourceId, values_) {

        val values = values_;
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var v = convertView
            if (v == null) {
                val vi = getSystemService(context, LayoutInflater::class.java) as LayoutInflater?
                val g: ViewGroup? = null
                if (vi != null) {
                    v = vi.inflate(R.layout.transaction_item, g)
                }
            }
            val d: TransactionItem = values[position]
                var tv = v!!.findViewById<TextView>(R.id.amount_label)
                tv.setText(d.amount.toString())
                tv = v.findViewById<TextView>(R.id.comment_label)
                tv.setText(d.comment)
                tv = v.findViewById<TextView>(R.id.address_label)
                tv.setText(d.address)
            return v
        }
    }


}