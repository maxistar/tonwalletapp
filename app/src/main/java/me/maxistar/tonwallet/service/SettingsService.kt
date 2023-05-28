package me.maxistar.tonwallet.service

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.util.Log
import androidx.preference.PreferenceManager
import java.util.Locale


class SettingsService {

    fun storeWallet(context: Context, address: String, secret: String) {
        val settings: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = settings.edit()
        editor.putString("address", address)
        editor.putString("secret", secret)
        editor.apply()
    }

    fun getTonConfiguration(context: Context): String {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getString("configurl", "https://ton-blockchain.github.io/global.config.json") as String
    }

    fun getWalletAddress(context: Context): String {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getString("address", "") as String
    }

    fun getWalletSecretPhrase(context: Context): String {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getString("secret", "") as String
    }

    fun getWalletVersion(context: Context): Long {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val version = sharedPreferences.getString("walletversion", "32") as String
        Log.w("version", version)
        return version.toLong()
    }

    fun isWalletStored(context: Context): Boolean {
        return (getWalletAddress(context) !== "" && getWalletSecretPhrase(context) !== "")
    }

    fun resetWallet(context: Context) {
        val settings: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = settings.edit()
        editor.remove("address")
        editor.remove("secret")
        editor.remove("access")
        editor.remove("biometric")
        editor.apply()
    }


    fun securityKeyStored(context: Context): Boolean {
        val key = getSecurityKey(context)
        return key.length > 3
    }
    fun storeSecurityKey(context: Context, key: String) {
        val settings: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = settings.edit()
        editor.putString("access", key)
        editor.apply()
    }

    fun getSecurityKey(context: Context): String {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getString("access", "") as String
    }

    fun setUseBiometric(context: Context, value: Boolean) {
        val settings: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = settings.edit()
        editor.putBoolean("biometric", value)
        editor.apply()
    }
    fun getUseBiometric(context: Context): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getBoolean("biometric", false)
    }

    fun getLanguage(context: Context): String {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val language = sharedPref.getString("language", "") as String;
        return language
    }
    fun applyLocale(context: Context) {
        val lang = getLanguage(context) as String
        if ("" == lang) {
            return  //use system default
        } else {
            val locale2 = Locale(lang)
            Locale.setDefault(locale2)
            val config2 = Configuration()
            config2.locale = locale2
            context.resources.updateConfiguration(config2, null)
        }
    }
}