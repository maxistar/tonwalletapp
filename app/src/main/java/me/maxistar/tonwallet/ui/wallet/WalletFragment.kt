package me.maxistar.tonwallet.ui.wallet

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Job
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.ReceiveActivity
import me.maxistar.tonwallet.SendActivity
import me.maxistar.tonwallet.databinding.FragmentWalletBinding
import me.maxistar.tonwallet.model.TransactionDisplayItem
import me.maxistar.tonwallet.service.ServiceProvider
import me.maxistar.tonwallet.util.TonFormatter


open class WalletFragment : Fragment() {

    companion object {
        fun newInstance() = WalletFragment()
    }

    private lateinit var viewModel: WalletViewModel

    private var binding: FragmentWalletBinding? = null

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settingsService = ServiceProvider.getSettingsService()

        viewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
        job = viewModel.updateWallet(
            settingsService.getWalletSecretPhrase(requireContext()),
            settingsService.getWalletVersion(requireContext()),
            settingsService.getTonConfiguration(requireContext())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletBinding.inflate(inflater, container, false)


        val buttonSend = binding!!.buttonSend
        buttonSend.setOnClickListener {
            val intent = Intent(this.context, SendActivity::class.java)
            startActivity(intent)
        }

        val buttonReceive = binding!!.buttonReceive
        buttonReceive.setOnClickListener {
            val intent = Intent(this.context, ReceiveActivity::class.java)
            startActivity(intent)
        }


        val settingsService = ServiceProvider.getSettingsService()
        val textViewAddress = binding!!.walletAddressShort
        textViewAddress.text =
            TonFormatter.addressShorten(settingsService.getWalletAddress(requireContext()))
        val textYourWalletAddress = binding!!.yourWalletAddress
        textYourWalletAddress.text = settingsService.getWalletAddress(requireContext())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            buttonReceive.setText(
                Html.fromHtml(
                    "<img src=\"receive_icon_24dp\"> Receive",
                    ImageGetter(requireContext()),
                    null
                ),
                TextView.BufferType.SPANNABLE
            )
            buttonSend.setText(
                Html.fromHtml(
                    "<img src=\"send_icon_24dp\"> Send",
                    ImageGetter(requireContext()),
                    null
                ),
                TextView.BufferType.SPANNABLE
            )
        }

        val textView = binding!!.walletAddressBalance
        viewModel.balance.observe(viewLifecycleOwner) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.setText(
                    Html.fromHtml(
                        TonFormatter.nanoTonsToHtmlString(it),
                        Html.FROM_HTML_MODE_COMPACT
                    ),
                    TextView.BufferType.SPANNABLE
                )
            } else {
                textView.setText(TonFormatter.nanoTonsToString(it))
            }

            job = null
        }

        val listView = binding!!.transactions

        listView.emptyView = binding!!.noTransactions

        viewModel.transactions.observe(viewLifecycleOwner) {
            listView.adapter =
                viewModel.transactions.let {
                    it.value?.let { it1 ->
                        TransactionsAdapter(
                            requireContext(),
                            R.layout.transaction_item,
                            it1.toList()
                        )
                    }
                }
            (listView.adapter as TransactionsAdapter?)?.notifyDataSetChanged()
        }

        // registerComponentCallbacks
        binding?.apply { registerForContextMenu(binding!!.walletTopPart) }

        val image = binding!!.root.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.ton_image_chicken)
        image.setAnimation(R.raw.created)
        image.playAnimation()

        return binding!!.root
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Pick option")
        requireActivity().menuInflater.inflate(R.menu.wallet_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                refreshWallet()
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun refreshWallet() {
        val settingsService = ServiceProvider.getSettingsService()

        job?.cancel()

        job = viewModel.updateWallet(
            settingsService.getWalletSecretPhrase(requireContext()),
            settingsService.getWalletVersion(requireContext()),
            settingsService.getTonConfiguration(requireContext())
        )
    }

    protected class TransactionsAdapter(
        context: Context,
        textViewResourceId: Int,
        values_: List<TransactionDisplayItem>
    ) :
        ArrayAdapter<TransactionDisplayItem?>(context, textViewResourceId, values_) {

        private val values = values_
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var v = convertView
            if (v == null) {
                val vi = getSystemService(context, LayoutInflater::class.java)
                val g: ViewGroup? = null
                if (vi != null) {
                    v = vi.inflate(R.layout.transaction_item, g)
                }
            }
            val d: TransactionDisplayItem = values[position]
            var tv = v!!.findViewById<TextView>(R.id.amount_label)
            val tonAmount = TonFormatter.nanoTonsToString(d.amount)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                if (d.transactionType == "in") {
                    tv.setText(
                        Html.fromHtml(context.resources.getString(R.string.wallet__transaction_html_from).format(tonAmount), Html.FROM_HTML_MODE_COMPACT),
                        TextView.BufferType.SPANNABLE
                    )
                } else {
                    tv.setText(
                        Html.fromHtml(context.resources.getString(R.string.wallet__transaction_html_to).format(tonAmount), Html.FROM_HTML_MODE_COMPACT),
                        TextView.BufferType.SPANNABLE
                    )
                }
            } else {
                if (d.transactionType == "in") {
                    tv.setText(context.resources.getString(R.string.wallet__transaction_from).format(tonAmount))
                } else {
                    tv.setText(context.resources.getString(R.string.wallet__transaction_to).format(tonAmount))
                }
            }


            tv = v.findViewById(R.id.comment_label)
            tv.setText(d.comment)
            tv = v.findViewById(R.id.address_label)
            tv.setText(TonFormatter.addressShorten(d.address))

            tv = v.findViewById(R.id.time_label)
            tv.setText(d.time)

            tv = v.findViewById(R.id.amount_day)
            if (d.date !== "") {
                tv.setText(d.date)
                tv.visibility = View.VISIBLE
            } else {
                tv.visibility = View.GONE
            }
            return v
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        job?.cancel()
        job = null
    }

    class ImageGetter(val context: Context) : Html.ImageGetter {

        override fun getDrawable(source: String): Drawable {
            var id: Int
            id = context.resources.getIdentifier(source, "drawable", context.getPackageName())
            if (id == 0) {
                // the drawable resource wasn't found in our package, maybe it is a stock android drawable?
                id = context.resources.getIdentifier(source, "drawable", "android")
            }
            return if (id == 0) {
                val d: Drawable = context.resources.getDrawable(R.drawable.receive_icon_24dp)
                d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight)
                d
            } else {
                val d: Drawable = context.resources.getDrawable(id)
                d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight)
                d
            }
        }
    }

}