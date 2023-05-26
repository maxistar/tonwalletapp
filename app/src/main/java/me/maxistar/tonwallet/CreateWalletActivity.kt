package me.maxistar.tonwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.maxistar.tonwallet.service.ServiceProvider
import me.maxistar.tonwallet.ui.create_wallet.CreateWalletFragment
import me.maxistar.tonwallet.ui.create_wallet.CreateWalletStartFragment

class CreateWalletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wallet)

        val scanFlag = intent.getBooleanExtra("showSeed", false)
        if (scanFlag) {
            val settingsService = ServiceProvider.getSettingsService()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CreateWalletFragment.newInstance(
                    settingsService.getWalletSecretPhrase(this),
                    settingsService.getWalletAddress(this)
                ))
                .commitNow()
            return
        } else if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CreateWalletStartFragment.newInstance())
                .commitNow()
        }
    }

    public fun switchActivity() {

    }
}