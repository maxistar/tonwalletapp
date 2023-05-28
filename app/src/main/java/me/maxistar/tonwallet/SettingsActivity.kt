package me.maxistar.tonwallet

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import me.maxistar.tonwallet.service.ServiceProvider
import me.maxistar.tonwallet.ui.start.StartScreenWalletFragment
import java.util.Locale


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /*
        val callback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                val intent = Intent(applicationContext, WalletActivity::class.java)
                startActivity(intent)
            }
        }
        onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        ) */
    }


    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            val deleteWalletPreference = findPreference<Preference>("delete_wallet")
            deleteWalletPreference?.layoutResource = R.layout.delete_wallet_preference_item
            deleteWalletPreference?.setOnPreferenceClickListener {
                resetUserConfirmation()
                true
            }


            val pinWalletPreference = findPreference<Preference>("passcode")
            pinWalletPreference?.setOnPreferenceClickListener {
                setPassCode()
                true
            }

            val seedPreference = findPreference<Preference>("phrase")
            seedPreference?.setOnPreferenceClickListener {
                showSeed()
                true
            }

            val languagePreference = findPreference<Preference>("language")
            languagePreference?.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference, newValue ->
                    val settingsService = ServiceProvider.getSettingsService()

                    settingsService.applyLocale(requireContext())

                    val locale2 = Locale(settingsService.getLanguage(requireContext()))
                    Locale.setDefault(locale2)
                    val config2 = Configuration()
                    config2.locale = locale2

                    requireContext().getResources()
                        .updateConfiguration(config2, null)


                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(context, SettingsActivity::class.java)
                        startActivity(intent)
                    }, 500)

                    return@OnPreferenceChangeListener true
                }

            val pInfo: PackageInfo
            val owner = requireActivity()
            pInfo = owner.packageManager.getPackageInfo(owner.packageName, 0)
            findPreference<Preference>("version_name")?.summary = pInfo.versionName

        }

        private fun showSeed() {
            val intent = Intent(context, CreateWalletActivity::class.java)
            intent.putExtra("showSeed", true)
            startActivity(intent)
        }

        private fun setPassCode() {
            val intent = Intent(context, AccessCodeActivity::class.java)
            intent.putExtra("changePasscode", true)
            startActivity(intent)
        }

        private fun resetUserConfirmation() {
            AlertDialog.Builder(context)
                .setTitle(R.string.Warning)
                .setMessage(R.string.User_Data_Is_About_To_Delete)
                .setPositiveButton(
                    R.string.general_Yes,
                    DialogInterface.OnClickListener { dialog, whichButton -> resetWallet() })
                .setNegativeButton(R.string.general_No, null).show()
        }

        private fun resetWallet() {
            val settingsService = ServiceProvider.getSettingsService()
            settingsService.resetWallet(requireContext())

            val intent = Intent(context, StartScreenActivity::class.java)
            startActivity(intent)
        }

    }
}


