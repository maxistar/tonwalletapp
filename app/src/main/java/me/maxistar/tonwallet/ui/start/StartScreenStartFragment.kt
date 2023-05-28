package me.maxistar.tonwallet.ui.start

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import me.maxistar.tonwallet.R

/**
 * A simple [Fragment] subclass.
 * Use the [StartScreenStartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartScreenStartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler(Looper.getMainLooper()).postDelayed({
            val fm: FragmentManager = parentFragmentManager
            fm.beginTransaction()
                .replace(R.id.container, StartScreenWalletFragment.newInstance("", ""))
                .addToBackStack(null)
                .commit()
        }, 2000)


        val root = inflater.inflate(R.layout.fragment_start_screen_start, container, false)

        val image = root.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.image_logo)
        image.setAnimation(R.raw.main)
        image.playAnimation()

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartScreenStartFragment()
    }
}