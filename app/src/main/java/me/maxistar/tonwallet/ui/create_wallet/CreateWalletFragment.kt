package me.maxistar.tonwallet.ui.create_wallet

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import me.maxistar.tonwallet.R

private const val ARG_PARAM1 = "seed"
private const val ARG_PARAM2 = "address"

class CreateWalletFragment : Fragment() {

    companion object {
        fun newInstance(seed: String, address: String) = CreateWalletFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, seed)
                putString(ARG_PARAM2, address)
            }
        }
    }

    private lateinit var seed: String

    private lateinit var address: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            seed = it.getString(ARG_PARAM1)!!
            address = it.getString(ARG_PARAM2)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_create_wallet, container, false)

        val word0: TextView = root.findViewById((R.id.word_0_value))
        val word1: TextView = root.findViewById((R.id.word_1_value))
        val word2: TextView = root.findViewById((R.id.word_2_value))
        val word3: TextView = root.findViewById((R.id.word_3_value))
        val word4: TextView = root.findViewById((R.id.word_4_value))
        val word5: TextView = root.findViewById((R.id.word_5_value))
        val word6: TextView = root.findViewById((R.id.word_6_value))
        val word7: TextView = root.findViewById((R.id.word_7_value))
        val word8: TextView = root.findViewById((R.id.word_8_value))
        val word9: TextView = root.findViewById((R.id.word_9_value))
        val word10: TextView = root.findViewById((R.id.word_10_value))
        val word11: TextView = root.findViewById((R.id.word_11_value))
        val word12: TextView = root.findViewById((R.id.word_12_value))
        val word13: TextView = root.findViewById((R.id.word_13_value))
        val word14: TextView = root.findViewById((R.id.word_14_value))
        val word15: TextView = root.findViewById((R.id.word_15_value))
        val word16: TextView = root.findViewById((R.id.word_16_value))
        val word17: TextView = root.findViewById((R.id.word_17_value))
        val word18: TextView = root.findViewById((R.id.word_18_value))
        val word19: TextView = root.findViewById((R.id.word_19_value))
        val word20: TextView = root.findViewById((R.id.word_20_value))
        val word21: TextView = root.findViewById((R.id.word_21_value))
        val word22: TextView = root.findViewById((R.id.word_22_value))
        val word23: TextView = root.findViewById((R.id.word_23_value))


        val wordsStr = seed
        val words = wordsStr.split("\\s".toRegex())
        word0.text = words[0]
        word1.text = words[1]
        word2.text = words[2]
        word3.text = words[3]
        word4.text = words[4]
        word5.text = words[5]
        word6.text = words[6]
        word7.text = words[7]
        word8.text = words[8]
        word9.text = words[9]
        word10.text = words[10]
        word11.text = words[11]
        word12.text = words[12]
        word13.text = words[13]
        word14.text = words[14]
        word15.text = words[15]
        word16.text = words[16]
        word17.text = words[17]
        word18.text = words[18]
        word19.text = words[19]
        word20.text = words[20]
        word21.text = words[21]
        word22.text = words[22]
        word23.text = words[23]


        val button = root.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            showConfirmation(it.context)
        }

        val image = root.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.ton_wallet_logo)
        image.setAnimation(R.raw.recovery_phrase)
        image.playAnimation()

        return root
    }

    @Throws(Resources.NotFoundException::class)
    private fun showConfirmation(context: Context) {
        AlertDialog.Builder(context)
            .setTitle(resources.getString(R.string.create_wallet_confirmation_title))
            .setMessage(
                resources.getString(R.string.create_wallet_confirmation_text)
            )
            .setPositiveButton(
                resources.getString(R.string.create_wallet_confirmation_skip_button)
            ) { _, _ ->
                val fm: FragmentManager = parentFragmentManager
                fm.beginTransaction()
                    .replace(
                        R.id.container, CreateWalletCheckFragment.newInstance(
                            seed,
                            address
                        )
                    )
                    .addToBackStack(null)
                    .commit()
            }
            .setNegativeButton(
                resources.getString(R.string.create_wallet_confirmation_ok_sorry_button)
            ) { _, _ ->
                //Do Something Here
            }.show()
    }
}