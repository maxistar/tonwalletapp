package me.maxistar.tonwallet.ui.create_wallet

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
import me.maxistar.tonwallet.AccessCodeActivity
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.databinding.FragmentCreateWalletCheckBinding
import me.maxistar.tonwallet.model.MemoWords
import me.maxistar.tonwallet.service.ServiceProvider

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

    private var binding: FragmentCreateWalletCheckBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            seed = it.getString(ARG_PARAM1)
            address = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateWalletCheckBinding.inflate(inflater, container, false)
        val root = binding!!.root


        val settings = ServiceProvider.getSettingsService()

        val button = root.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            if (!seedIsValid()) {
                showErrorMessage()
            } else {
                settings.storeWallet(context!!, address!!, seed!!)
                val intent = Intent(context, AccessCodeActivity::class.java)
                startActivity(intent)
            }
        }

        setupAutosuggestions(context!!, root)

        val image = root.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.ton_image_layout)
        image.setAnimation(R.raw.test_time)
        image.playAnimation()

        return root
    }

    private fun seedIsValid(): Boolean {
        val word1:String = binding!!.testWord1.text.toString()
        val word2:String = binding!!.testWord2.text.toString()
        val word3:String = binding!!.testWord3.text.toString()

        //1, 5, 18 are hardcoded jungle reduce expect
        val words = seed!!.split("\\s".toRegex())
        if (words[0] != word1 || words[4] != word2 || words[17] != word3) {
            return false
        }
        return true
    }

    private fun showErrorMessage() {
        AlertDialog.Builder(context)
            .setTitle(R.string.import_wallet__error_dialog__title)
            .setMessage(R.string.import_wallet__error_dialog__text)
            .setPositiveButton(
                R.string.general__ok
            ) { _, _ -> nothingToDo() }
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
    }

    private fun setAdapterForControl(root: View, controlId: Int, adapter: ArrayAdapter<String>) {
        val control = root.findViewById(controlId) as AutoCompleteTextView
        control.threshold = 1
        control.setAdapter(adapter)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param seed Parameter 1.
         * @param address Parameter 2.
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}