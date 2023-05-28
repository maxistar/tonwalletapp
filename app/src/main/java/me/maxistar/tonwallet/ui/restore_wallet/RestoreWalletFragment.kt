package me.maxistar.tonwallet.ui.restore_wallet

import android.app.AlertDialog
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
import me.maxistar.tonwallet.service.ServiceProvider


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
            if (tryToRestoreWallet(root)) {
                val intent = Intent(context, AccessCodeActivity::class.java)
                startActivity(intent)
            } else {
                showErrorMessage()
            }
        }

        val errorButton = root.findViewById<Button>(R.id.button_do_not_have)
        errorButton.setOnClickListener {
            val fm: FragmentManager = parentFragmentManager
            fm.beginTransaction()
                .replace(R.id.container, RestoreWalletFailureFragment.newInstance("", ""))
                .addToBackStack(null)
                .commit()
        }

        val image = root.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.ton_image_layout)
        image.setAnimation(R.raw.recovery_phrase)
        image.playAnimation()

        setupAutosuggestions(requireContext(), root)

        return root;
    }

    private fun tryToRestoreWallet(view: View): Boolean {

        try {
            val seed = getSeed(view)
            val walletService = ServiceProvider.getWalletService()
            val settingsService = ServiceProvider.getSettingsService()
            val info = walletService.loadWalletInfo(seed, settingsService.getWalletVersion(requireContext()), settingsService.getTonConfiguration(requireContext()))

            if (info.getPublicAddress() !== "") {
                settingsService.storeWallet(requireContext(), info.getPublicAddress(), info.getSeed())
                return true
            }
        } catch (e: Throwable) {
            // log error
        }

        return false;
    }

    private fun getSeed(root: View): String {
        val words = arrayOf(
            root.findViewById<AutoCompleteTextView>(R.id.test_word1).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word2).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word3).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word4).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word5).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word6).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word7).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word8).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word9).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word10).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word11).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word12).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word13).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word14).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word15).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word16).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word17).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word18).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word19).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word20).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word21).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word22).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word23).text,
            root.findViewById<AutoCompleteTextView>(R.id.test_word24).text,
        );
        return words.joinToString(separator = " ")
    }

    private fun showErrorMessage() {
        AlertDialog.Builder(context)
            .setTitle(R.string.import_wallet__error_dialog__title)
            .setMessage(R.string.import_wallet__error_dialog__text)
            .setIcon(R.drawable.ic_launcher_background)
            .setPositiveButton(
                R.string.general__ok,
                { dialog, whichButton -> nothingToDo() })
            .show()
    }

    private fun nothingToDo() {
        // TODO("Not yet implemented")
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