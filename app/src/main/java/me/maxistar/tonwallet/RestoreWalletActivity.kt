package me.maxistar.tonwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.maxistar.tonwallet.ui.restore_wallet.RestoreWalletFragment

class RestoreWalletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore_wallet)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RestoreWalletFragment.newInstance())
                .commitNow()
        }
    }
}