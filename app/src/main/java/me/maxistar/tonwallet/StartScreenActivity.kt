package me.maxistar.tonwallet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.maxistar.tonwallet.service.ServiceProvider
import me.maxistar.tonwallet.ui.start.StartScreenStartFragment

class StartScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)
        val settingsService = ServiceProvider.getSettingsService()
        if (settingsService.isWalletStored(this)) {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StartScreenStartFragment.newInstance())
                .commitNow()
        }
    }
}
