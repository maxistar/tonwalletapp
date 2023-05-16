package com.example.tonwalletactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tonwalletactivities.ui.wallet.WalletFragment

class WalletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WalletFragment.newInstance())
                .commitNow()
        }
    }
}