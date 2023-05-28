package me.maxistar.tonwallet.ui.access_code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.service.ServiceProvider

class AccessCodeStartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(
            R.layout.fragment_access_code_start,
            container,
            false
        )

        val checkBox = root.findViewById<CheckBox>(R.id.use_biometric)

        val button = root.findViewById<Button>(R.id.button)
        button.setOnClickListener {

            ServiceProvider.getSettingsService().setUseBiometric(requireContext(), checkBox.isChecked)

            val fm: FragmentManager = parentFragmentManager
            fm.beginTransaction()
                .replace(R.id.container, AccessCodeFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }

        val image = root.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.ton_wallet_logo)
        image.setAnimation(R.raw.success)
        image.playAnimation()

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CreateWalletAfterCreationConfirmationFragment.
         */
        @JvmStatic
        fun newInstance() = AccessCodeStartFragment()
    }
}