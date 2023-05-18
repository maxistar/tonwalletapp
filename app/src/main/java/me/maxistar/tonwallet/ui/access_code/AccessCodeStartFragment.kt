package me.maxistar.tonwallet.ui.access_code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import me.maxistar.tonwallet.R

/**
 * A simple [Fragment] subclass.
 * Use the [AccessCodeStartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccessCodeStartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(
            R.layout.fragment_access_code_start,
            container,
            false
        )

        val button = root.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            // val intent = Intent(it.context, AccessCodeActivity::class.java)
            // startActivity(intent)
            val fm: FragmentManager = parentFragmentManager
            fm.beginTransaction()
                .replace(R.id.container, AccessCodeFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }

        return root;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateWalletAfterCreationConfirmationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = AccessCodeStartFragment()
    }
}