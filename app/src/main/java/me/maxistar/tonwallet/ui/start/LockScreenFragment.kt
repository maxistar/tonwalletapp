package me.maxistar.tonwallet.ui.start

import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.WalletActivity
import me.maxistar.tonwallet.service.ServiceProvider
import me.maxistar.tonwallet.ui.access_code.AccessCodeViewModel

class LockScreenFragment : Fragment() {

    companion object {
        fun newInstance() = LockScreenFragment()
    }

    private lateinit var viewModel: LockScreenViewModel

    private lateinit var pointImage1: ImageView
    private lateinit var pointImage2: ImageView
    private lateinit var pointImage3: ImageView
    private lateinit var pointImage4: ImageView
    private lateinit var pointImage5: ImageView
    private lateinit var pointImage6: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_lock_screen, container, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            decorateButtons(root!!)
        }

        pointImage1 = root.findViewById<ImageView>(R.id.imageView0)
        pointImage2 = root.findViewById<ImageView>(R.id.imageView1)
        pointImage3 = root.findViewById<ImageView>(R.id.imageView2)
        pointImage4 = root.findViewById<ImageView>(R.id.imageView3)
        pointImage5 = root.findViewById<ImageView>(R.id.imageView4)
        pointImage6 = root.findViewById<ImageView>(R.id.imageView5)

        viewModel.liveReady.observe(viewLifecycleOwner) {
            if (it) {
                pointImage1.setImageResource(R.drawable.point_green_24dp)
                pointImage2.setImageResource(R.drawable.point_green_24dp)
                pointImage3.setImageResource(R.drawable.point_green_24dp)
                pointImage4.setImageResource(R.drawable.point_green_24dp)
                pointImage5.setImageResource(R.drawable.point_green_24dp)
                pointImage6.setImageResource(R.drawable.point_green_24dp)

                gotoWallet();
            }
        }

        viewModel.liveStep.observe(viewLifecycleOwner) {
            showPoints(it)
        }

        viewModel.liveError.observe(viewLifecycleOwner) {
            if (it == true) {
                val arr = arrayOf(pointImage1, pointImage2, pointImage3, pointImage4, pointImage5, pointImage6)
                for (point in arr) {
                    point.setImageResource(R.drawable.point_red_24dp)
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    for (point in arr) {
                        point.setImageResource(R.drawable.point_empty_24dp)
                    }
                }, 500)
            }
        }

        setupButtonEventHandlers(root!!);

        val pin = ServiceProvider.getSettingsService().getSecurityKey(context!!);
        if (pin.length > 4) {
            pointImage5.visibility = ImageView.VISIBLE
            pointImage6.visibility = ImageView.VISIBLE
        }

        val image =
            root.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.fragment_central_image)
        image.setAnimation(R.raw.password)
        image.playAnimation()

        val successCallback = {
            gotoWallet()
        }

        val failureCallback = {

        }

        if (ServiceProvider.getSettingsService().getUseBiometric(context!!)) {
            ServiceProvider.getFingerprintService().authenticate(this, successCallback, failureCallback)
        }

        return root
    }

    private fun gotoWallet() {
        val intent = Intent(context, WalletActivity::class.java)
        startActivity(intent)
    }


    private fun showPoints(step: Int) {
        val arr = arrayOf(pointImage1, pointImage2, pointImage3, pointImage4, pointImage5, pointImage6)
        for ((index, point) in arr.withIndex()) {
            if (index < step) {
                point.setImageResource(R.drawable.point_filled_24dp)
            } else {
                point.setImageResource(R.drawable.point_empty_24dp)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LockScreenViewModel::class.java)
        val pin = ServiceProvider.getSettingsService().getSecurityKey(context!!);
        viewModel.setCode(pin)
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
    private fun decorateButtons(root: View) {
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
            TextView.BufferType.SPANNABLE
        )

    }

}