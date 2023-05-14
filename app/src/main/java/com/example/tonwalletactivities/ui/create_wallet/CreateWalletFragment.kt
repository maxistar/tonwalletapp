package com.example.tonwalletactivities.ui.create_wallet

import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.tonwalletactivities.R

class CreateWalletFragment : Fragment() {

    companion object {
        fun newInstance() = CreateWalletFragment()
    }

    private lateinit var viewModel: CreateWalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateWalletViewModel::class.java)


        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_create_wallet, container, false)

        val word0: TextView = root.findViewById((R.id.word_0_value));
        val word1: TextView = root.findViewById((R.id.word_1_value));
        val word2: TextView = root.findViewById((R.id.word_2_value));
        val word3: TextView = root.findViewById((R.id.word_3_value));
        val word4: TextView = root.findViewById((R.id.word_4_value));
        val word5: TextView = root.findViewById((R.id.word_5_value));
        val word6: TextView = root.findViewById((R.id.word_6_value));
        val word7: TextView = root.findViewById((R.id.word_7_value));
        val word8: TextView = root.findViewById((R.id.word_8_value));
        val word9: TextView = root.findViewById((R.id.word_9_value));
        val word10: TextView = root.findViewById((R.id.word_10_value));
        val word11: TextView = root.findViewById((R.id.word_11_value));
        val word12: TextView = root.findViewById((R.id.word_12_value));
        val word13: TextView = root.findViewById((R.id.word_13_value));
        val word14: TextView = root.findViewById((R.id.word_14_value));
        val word15: TextView = root.findViewById((R.id.word_15_value));
        val word16: TextView = root.findViewById((R.id.word_16_value));
        val word17: TextView = root.findViewById((R.id.word_17_value));
        val word18: TextView = root.findViewById((R.id.word_18_value));
        val word19: TextView = root.findViewById((R.id.word_19_value));
        val word20: TextView = root.findViewById((R.id.word_20_value));
        val word21: TextView = root.findViewById((R.id.word_21_value));
        val word22: TextView = root.findViewById((R.id.word_22_value));
        val word23: TextView = root.findViewById((R.id.word_23_value));


        viewModel.text.observe(viewLifecycleOwner) {
            val words = it.getWords()
            word0.text = words.get(0);
            word1.text = words.get(1);
            word2.text = words.get(2);
            word3.text = words.get(3);
            word4.text = words.get(4);
            word5.text = words.get(5);
            word6.text = words.get(6);
            word7.text = words.get(7);
            word8.text = words.get(8);
            word9.text = words.get(9);
            word10.text = words.get(10);
            word11.text = words.get(11);
            word12.text = words.get(12);
            word13.text = words.get(13);
            word14.text = words.get(14);
            word15.text = words.get(15);
            word16.text = words.get(16);
            word17.text = words.get(17);
            word18.text = words.get(18);
            word19.text = words.get(19);
            word20.text = words.get(20);
            word21.text = words.get(21);
            word22.text = words.get(22);
            word23.text = words.get(23);
        }

        val button = root.findViewById<Button>(R.id.button);

        button.setOnClickListener {
            showConfirmation(it.context)
        }

        return root
    }

    /**
     * @throws NotFoundException
     */
    @Throws(Resources.NotFoundException::class)
    private fun showConfirmation(context: Context) {
        AlertDialog.Builder(context)
            .setTitle(getResources().getString(R.string.create_wallet_confirmation_title))
            .setMessage(
                getResources().getString(R.string.create_wallet_confirmation_text)
            )
            .setPositiveButton(
                getResources().getString(R.string.create_wallet_confirmation_skip_button),
                DialogInterface.OnClickListener { dialog, which ->
                    //Do Something Here
                })
            .setNegativeButton(
                getResources().getString(R.string.create_wallet_confirmation_ok_sorry_button),
                DialogInterface.OnClickListener { dialog, which ->
                    //Do Something Here
                }).show()
    }
}