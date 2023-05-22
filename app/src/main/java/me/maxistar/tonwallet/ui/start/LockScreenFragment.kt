package me.maxistar.tonwallet.ui.start

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
import me.maxistar.tonwallet.service.ServiceProvider
import me.maxistar.tonwallet.ui.access_code.AccessCodeViewModel

class LockScreenFragment : Fragment() {

    companion object {
        fun newInstance() = LockScreenFragment()
    }

    private lateinit var viewModel: LockScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_lock_screen, container, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            decorateButtons(root!!)
        }

        viewModel.liveReady.observe(viewLifecycleOwner) {
            if (it) {
                val intent = Intent(context, WalletActivity::class.java)
                startActivity(intent)            }
        }

        setupButtonEventHandlers(root!!);

        val image = root.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.fragment_central_image)
        image.setAnimation(R.raw.password)
        image.playAnimation()

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LockScreenViewModel::class.java)
        viewModel.code.setCode(ServiceProvider.getSettingsService().getSecurityKey(context!!))
    }

    private fun setupButtonEventHandlers(root: View) {
        setupButton(root, R.id.access_code_button_1, '1')
        setupButton(root, R.id.access_code_button_2, '2')
        setupButton(root, R.id.access_code_button_3, '3')
        setupButton(root, R.id.access_code_button_4, '4')
        setupButton(root, R.id.access_code_button_5, '5')
        setupButton(root, R.id.access_code_button_6, '6')
        setupButton(root, R.id.access_code_button_7, '7')
        setupButton(root, R.id.access_code_button_8, '8')
        setupButton(root, R.id.access_code_button_9, '9')
        setupButton(root, R.id.access_code_button_0, '0')
        setupButton(root, R.id.access_code_button_delete, 'd')
    }

    private fun setupButton(root: View, accessCodeButton1: Int, s: Char) {
        val button = root.findViewById<Button>(accessCodeButton1)
        button.setOnClickListener {
            viewModel.setCharacter(s);
        }
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