package me.maxistar.tonwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.maxistar.tonwallet.ui.create_wallet.CreateWalletStartFragment

class CreateWalletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wallet)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CreateWalletStartFragment.newInstance())
                //.replace(R.id.container, CreateWalletFragment.newInstance())
                .commitNow()
        }
    }

    public fun switchActivity() {

    }
}