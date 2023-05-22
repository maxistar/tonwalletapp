package me.maxistar.tonwallet.ui.create_wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import me.maxistar.tonwallet.R

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class CreateWalletStartFragment : Fragment() {

    private var dummyButton: Button? = null
    private var fullscreenContent: View? = null
    private var fullscreenContentControls: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_create_wallet_start, container, false)


        val button = root.findViewById<Button>(R.id.button)

        button.setOnClickListener { gotoNextFragment() }

        val image = root.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.ton_image_layout)
        image.setAnimation(R.raw.congrats)
        image.playAnimation()

        return root
    }

    private fun gotoNextFragment() {
        //Log.d("test", "clicked")
        switchFragment(CreateWalletFragment.newInstance())
    }

    private fun switchFragment(fragment: Fragment) {
        val fm: FragmentManager = parentFragmentManager
        fm.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        dummyButton = null
        fullscreenContent = null
        fullscreenContentControls = null
    }
    companion object {
            fun newInstance() = CreateWalletStartFragment()
    }
}