package com.example.tonwalletactivities.ui.start

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.tonwalletactivities.R

/**
 * A simple [Fragment] subclass.
 * Use the [StartScreenStartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartScreenStartFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({
            //val intent = Intent(this.context, MainActivity::class.java)
            //startActivity(intent)
            val fm: FragmentManager = parentFragmentManager
            fm.beginTransaction()
                .replace(R.id.container, StartScreenWalletFragment.newInstance("", ""))
                .addToBackStack(null)
                .commit()
        }, 2000)


        return inflater.inflate(R.layout.fragment_start_screen_start, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment StartScreenStartFragment.
         */
        @JvmStatic
        fun newInstance() = StartScreenStartFragment()
    }
}