package me.maxistar.tonwallet.ui.wallet

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.ReceiveActivity
import me.maxistar.tonwallet.SendActivity
import me.maxistar.tonwallet.model.TransactionItem
import java.util.Locale


class WalletFragment : Fragment() {

    companion object {
        fun newInstance() = WalletFragment()
    }

    private lateinit var viewModel: WalletViewModel

    val values: Array<TransactionItem>(20) { TransactionItem(11, "comment", "address") }

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


    protected class DictionariesAdapter(context: Context?, textViewResourceId: Int) :
        ArrayAdapter<TransactionItem?>(context, textViewResourceId, values) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var v = convertView
            if (v == null) {
                val vi = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
                val g: ViewGroup? = null
                v = vi.inflate(R.layout.dictionary_item, g)
            }
            val d: DictionaryItem = values.get(position)
            if (d != null) {
                var tv = v!!.findViewById<TextView>(R.id.title)
                tv.setText(d.title)
                tv = v.findViewById<TextView>(R.id.lang_source)
                tv.setText(d.source)
                tv = v.findViewById<TextView>(R.id.lang_destination)
                tv.setText(d.destination)
                tv = v.findViewById<TextView>(R.id.words)
                val formatString: String
                formatString = if (d.toLearn < 2) {
                    DStrings.HTML_WORDS_MEANINGS_LEARN_ERRORS_GREEN
                } else {
                    DStrings.HTML_WORDS_MEANINGS_LEARN_ERRORS
                }
                tv.text = Html.fromHtml(
                    java.lang.String.format(
                        Locale.getDefault(),
                        formatString,
                        d.words,
                        d.meanings,
                        d.toLearn,
                        d.newWords
                    )
                )
            }
            return v!!
        }
    }


}