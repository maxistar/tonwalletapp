package me.maxistar.tonwallet.ui.access_code

import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.WalletActivity

class AccessCodeFragment : Fragment() {

    companion object {
        fun newInstance() = AccessCodeFragment()
    }

    private lateinit var viewModel: AccessCodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccessCodeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_access_code, container, false)

        val button = root.findViewById<Button>(R.id.access_code_button_0)
        button.setOnClickListener({
            val intent = Intent(context, WalletActivity::class.java)
            startActivity(intent)
        })


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            decorateButtons(root!!)
        }

        return root;
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun decorateButtons(root: View)
    {
        decorateButton(root, R.id.access_code_button_1, R.string.access_code_number_html_1)
        decorateButton(root, R.id.access_code_button_2, R.string.access_code_number_html_2)
        decorateButton(root, R.id.access_code_button_3, R.string.access_code_number_html_3)
        decorateButton(root, R.id.access_code_button_4, R.string.access_code_number_html_4)
        decorateButton(root, R.id.access_code_button_5, R.string.access_code_number_html_5)
        decorateButton(root, R.id.access_code_button_6, R.string.access_code_number_html_6)
        decorateButton(root, R.id.access_code_button_7, R.string.access_code_number_html_7)
        decorateButton(root, R.id.access_code_button_8, R.string.access_code_number_html_8)
        decorateButton(root, R.id.access_code_button_9, R.string.access_code_number_html_9)
        decorateButton(root, R.id.access_code_button_0, R.string.access_code_number_html_0)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun decorateButton(root: View, buttonId: Int, textId: Int) {
        val button3 = root.findViewById<Button>(buttonId)
        button3.setText(
            Html.fromHtml(getResources().getString(textId), Html.FROM_HTML_MODE_LEGACY),
            TextView.BufferType.SPANNABLE)

    }

}