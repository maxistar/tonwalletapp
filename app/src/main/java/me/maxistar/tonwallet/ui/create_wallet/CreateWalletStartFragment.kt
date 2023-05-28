package me.maxistar.tonwallet.ui.create_wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import me.maxistar.tonwallet.R
import me.maxistar.tonwallet.databinding.FragmentCreateWalletStartBinding
import me.maxistar.tonwallet.service.ServiceProvider

class CreateWalletStartFragment : Fragment() {

    private var binding: FragmentCreateWalletStartBinding? = null

    private lateinit var viewModel: CreateWalletViewModel

    private lateinit var seed: String

    private lateinit var address: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateWalletStartBinding.inflate(inflater, container, false)

        val root = binding!!.root

        val button = root.findViewById<Button>(R.id.button)

        button.setOnClickListener { gotoNextFragment() }

        val image1 = binding!!.tonImageGenerate
        image1.setAnimation(R.raw.loading)
        image1.playAnimation()

        val settingsService = ServiceProvider.getSettingsService()
        viewModel = ViewModelProvider(this)[CreateWalletViewModel::class.java]
        viewModel.generateNewWallet(settingsService.getWalletVersion(requireContext()), settingsService.getTonConfiguration(requireContext()))

        viewModel.text.observe(viewLifecycleOwner) {
            if (it === "") {
                return@observe
            }

            showSuccessMessage()
            seed = it
            address = viewModel.newWalletAddress
        }

        return root
    }

    private fun showSuccessMessage() {
        val image = binding!!.tonImageLayout
        image.visibility = View.VISIBLE
        image.setAnimation(R.raw.congrats)
        image.playAnimation()


        val image1 = binding!!.tonImageGenerate
        image1.visibility = View.GONE

        val title = binding!!.tonWalletTitle
        title.visibility = View.VISIBLE

        val description = binding!!.tonWalletDescription
        description.visibility = View.VISIBLE

        val button: Button = binding!!.button
        button.isEnabled = true
    }

    private fun gotoNextFragment() {
        switchFragment(CreateWalletFragment.newInstance(seed, address))
    }

    private fun switchFragment(fragment: Fragment) {
        val fm: FragmentManager = parentFragmentManager
        fm.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
            fun newInstance() = CreateWalletStartFragment()
    }
}