package me.maxistar.tonwallet

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import me.maxistar.tonwallet.service.ServiceProvider


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
            Log.w("preference", deleteWalletPreference?.layoutResource.toString())
            //R.styleable
            deleteWalletPreference?.layoutResource = R.layout.delete_wallet_preference_item
            deleteWalletPreference?.setOnPreferenceClickListener {
                resetUserConfirmation()
                true
            }
        }

        private fun resetUserConfirmation() {
            AlertDialog.Builder(context)
                .setTitle(R.string.Warning)
                .setMessage(R.string.User_Data_Is_About_To_Delete)
                .setIcon(R.drawable.ic_launcher_background)
                .setPositiveButton(
                    R.string.general_Yes,
                    DialogInterface.OnClickListener { dialog, whichButton -> resetWallet() })
                .setNegativeButton(R.string.general_No, null).show()
        }

        private fun resetWallet() {
            val settingsService = ServiceProvider.getSettingsService()
            settingsService.resetWallet(context!!)

            val intent = Intent(context, StartScreenActivity::class.java)
            startActivity(intent)
        }

    }
}