package me.maxistar.tonwallet

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
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
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val preference = findPreference<Preference>("delete_wallet")
            if (preference != null) {
                preference.setOnPreferenceClickListener({
                    resetUserConfirmation()
                    true
                })
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