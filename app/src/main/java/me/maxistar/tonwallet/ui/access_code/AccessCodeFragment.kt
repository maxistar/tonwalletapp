package me.maxistar.tonwallet.ui.access_code

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.WalletActivity
import me.maxistar.tonwallet.databinding.FragmentAccessCodeBinding
import me.maxistar.tonwallet.service.ServiceProvider

class AccessCodeFragment : Fragment() {

    private lateinit var pointImage1: ImageView
    private lateinit var pointImage2: ImageView
    private lateinit var pointImage3: ImageView
    private lateinit var pointImage4: ImageView
    private lateinit var pointImage5: ImageView
    private lateinit var pointImage6: ImageView

    companion object {
        fun newInstance() = AccessCodeFragment()
    }

    private lateinit var viewModel: AccessCodeViewModel

    private var binding: FragmentAccessCodeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccessCodeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccessCodeBinding.inflate(inflater, container, false)
        val root = binding!!.root

        pointImage1 = binding!!.imageView0
        pointImage2 = binding!!.imageView1
        pointImage3 = binding!!.imageView2
        pointImage4 = binding!!.imageView3
        pointImage5 = binding!!.imageView4
        pointImage6 = binding!!.imageView5


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            decorateButtons(root)
        }

        val title = binding!!.tonWalletTitle
        viewModel.liveEnterMode.observe(viewLifecycleOwner) {
            if (it) {
                title.setText(R.string.access_code_title)
            } else {
                title.setText(R.string.access_code_title_confirm)
            }
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



        viewModel.liveReady.observe(viewLifecycleOwner) {
            if (it) {
                val settingsService = ServiceProvider.getSettingsService()
                settingsService.storeSecurityKey(requireContext(), viewModel.code.toString())

                if (settingsService.getUseBiometric(requireContext())) {
                    showBiometric()
                } else {
                    goToWallet()
                }
            }
        }

        viewModel.liveStep.observe(viewLifecycleOwner) {
            showPoints(it)
        }

        setupButtonEventHandlers(root)

        val image = binding!!.fragmentCentralImage
        image.setAnimation(R.raw.password)
        image.playAnimation()

        binding?.apply { registerForContextMenu(binding!!.accessCodeOptionsText) }
        binding!!.accessCodeOptionsText.setOnClickListener {
            it.showContextMenu()
        }

        return root
    }

    private fun goToWallet() {
        val intent = Intent(context, WalletActivity::class.java)
        startActivity(intent)
    }

    private fun showBiometric() {
        val fingerprintService = ServiceProvider.getFingerprintService()
        val successCallback = {
            goToWallet()
        }
        val failureCallback = {
            ServiceProvider.getSettingsService().setUseBiometric(requireContext(), false)
            goToWallet()
        }
        fingerprintService.authenticate(this, successCallback, failureCallback)
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

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.access_code_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_access_code_4 -> {
                setAccessCodeLength4()
            }
            R.id.menu_access_code_6 -> {
                setAccessCodeLength6()
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun setAccessCodeLength6() {
        pointImage5.visibility = ImageView.VISIBLE
        pointImage6.visibility = ImageView.VISIBLE
        viewModel.setCodeLength(6)
    }

    private fun setAccessCodeLength4() {
        pointImage5.visibility = ImageView.GONE
        pointImage6.visibility = ImageView.GONE
        viewModel.setCodeLength(4)
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
            viewModel.setCharacter(s)
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
            Html.fromHtml(resources.getString(textId), Html.FROM_HTML_MODE_LEGACY),
            TextView.BufferType.SPANNABLE)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}